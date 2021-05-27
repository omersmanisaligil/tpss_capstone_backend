package com.tpss.ThirdPartySupplierSelection.mcdm.waspas;

import com.tpss.ThirdPartySupplierSelection.dto.ProviderDTO;
import com.tpss.ThirdPartySupplierSelection.dto.WaspasDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.mcdm.MCDMConstants;
import com.tpss.ThirdPartySupplierSelection.mcdm.MCDMCriteriaPoints;

import java.util.*;

public class WaspasImpl {

    private static double lambda = 0.5;

    public static WaspasDTO execute(List<Provider> list) {
        List<ProviderDTO> providerDTOs = MCDMCriteriaPoints.determineCriteriaPoints(list);
        HashMap<Long,double[]> providerMap = new HashMap<>();
        for (ProviderDTO p : providerDTOs) {
          //  System.out.println("criteria points: " + p.getCriteriaPoints());

            int[] intArrProviders = p.getCriteriaPoints();
            double[] doubleArrProviders = new double[17];
            for (int i = 0; i <intArrProviders.length ; i++) {
                doubleArrProviders[i] = intArrProviders[i];
            }

            providerMap.put(p.getProviderId(),doubleArrProviders);
        }

        double[] maxValues = findMaxForCriterias(providerMap.values());
        //System.out.println("max values: " + Arrays.toString(maxValues));

        normalizeMatrice(providerMap,maxValues);

        HashMap<Long,double[]> q1Matrice = Q1(providerMap);
        HashMap<Long,double[]> q2Matrice = Q2(providerMap);


        HashMap<Long,Double> wsmMap = WSM(q1Matrice);
        HashMap<Long,Double> wpmMap = WPM(q2Matrice);

        HashMap<Long,Double> ranked = rankProviders(wsmMap,wpmMap);

        WaspasDTO waspasDTO = new WaspasDTO(q1Matrice,q2Matrice,wsmMap,wpmMap,ranked,providerDTOs);

        return waspasDTO;
    }

    public static HashMap<Long,Double> rankProviders(HashMap<Long,Double> wsm, HashMap<Long,Double> wpm){
        HashMap<Long,Double> ranked = new HashMap<>();

        for (Long key : wsm.keySet()) {
            Double res = wsm.get(key)*lambda+(1-lambda)*wpm.get(key);
            ranked.put(key,res);
        }

        return ranked;
    }
    
    public static double[] findMaxForCriterias(Collection<double[]> list) {
        double[] maxArr = new double[17];

        for (int i = 0; i < maxArr.length; i++) {
            double maxI = -1;
            for (double[] a : list) {
                if (a[i] > maxI)
                    maxI = a[i];
            }
            maxArr[i] = maxI;
        }

        return maxArr;
    }

    public static void normalizeMatrice(HashMap<Long,double[]> hmProvider,double[] maxValues) {
        //firma kriter değeri/max değer
        for(Long key : hmProvider.keySet() ){
            double[] arrNormalize = new double[17];
            double[] unnormalizedArr = hmProvider.get(key);

            for (int i = 0; i<unnormalizedArr.length;i++){
                arrNormalize[i] = unnormalizedArr[i]/ maxValues[i];
            }
            hmProvider.put(key,arrNormalize);
        }
    }

    public static HashMap<Long,Double> WSM(HashMap<Long, double[]> hMapQ1) {
        HashMap<Long,Double> wsmMap = new HashMap<>();

        for (Long id :hMapQ1.keySet()) {
            Double sum = 0.0;
            double[] arr = hMapQ1.get(id);
            for (int i = 0; i <arr.length ; i++) {
                sum += arr[i];
            }
            wsmMap.put(id,sum);
        }

        return wsmMap;
    }

    public static HashMap<Long,Double> WPM(HashMap<Long, double[]> hMapQ2) {
        HashMap<Long,Double> wpmMap = new HashMap<>();

        for (Long id:hMapQ2.keySet()) {
            Double multiplied = 1.0;
            double[] arr = hMapQ2.get(id);
            for (int i = 0; i <arr.length ; i++) {
                multiplied *= arr[i];
            }
            wpmMap.put(id,multiplied);
        }

        return wpmMap;
    }

    public static HashMap<Long, double[]> Q1(HashMap<Long, double[]> hMapNormalized) {
        HashMap<Long, double[]> q1Map = new HashMap<>();

        for (Long id :hMapNormalized.keySet()){
            double[] arrQ1 = new double[17];
            double[] arr = hMapNormalized.get(id);
            for (int i = 0; i <arr.length; i++) {
                arrQ1[i] = arr[i] * MCDMConstants.weights[i];
            }
            q1Map.put(id,arrQ1);
        }

        return q1Map;
    }

    public static HashMap<Long, double[]> Q2(HashMap<Long, double[]> hMapNormalized) {
        HashMap<Long, double[]> q2Map = new HashMap<>();

        for(Long id:hMapNormalized.keySet()){
            double[] arrQ2 = new double[17];
            double[] arr = hMapNormalized.get(id);
            for (int i = 0; i <arr.length; i++) {
                arrQ2[i] = Math.pow(arr[i],MCDMConstants.weights[i]);
            }
            q2Map.put(id,arrQ2);
        }

        return q2Map;
    }


    /*public static void main(String[] args) {
        double[] arr0 = {3,4,4,3,3,3,2,4,3,2,3,4,2,2,1,4,3};
        double[] arr1 = {5,4,3,3,4,5,5,3,3,3,5,3,4,3,1,2,3};
        double[] arr2 = {4,3,3,2,4,3,3,3,3,4,3,4,2,2,1,4,3};
                    //res:5,4,4,3,4,5,5,4,3,4,5,4,4,3,1,4,3
        List<double[]> arrList = new ArrayList<>();
        arrList.add(arr0);
        arrList.add(arr2);
        arrList.add(arr1);

        double[] max = findMaxForCriterias(arrList);
        System.out.println("max arr" + Arrays.toString(max));

        HashMap<Long, double[]> matrice = new HashMap<>();
        matrice.put(Long.valueOf(1),arr0);
        matrice.put(Long.valueOf(2),arr1);
        matrice.put(Long.valueOf(3),arr2);


        normalizeMatrice(matrice,max);
        System.out.println("normalized matrice");
        for (Long key : matrice.keySet()){
            System.out.println("id: " + key + " points: " +Arrays.toString(matrice.get(key)));
        }

        System.out.println();

        HashMap<Long,double[]> q1Matrice = Q1(matrice);
        HashMap<Long,double[]> q2Matrice = Q2(matrice);

        System.out.println("Q1");
        for (Long key : q1Matrice.keySet()){
            System.out.println("id: " + key + " points: " + Arrays.toString(q1Matrice.get(key)));
        }
        System.out.println("Q2");
        for (Long key : q2Matrice.keySet()){
            System.out.println("id: " + key + " points: " + Arrays.toString(q2Matrice.get(key)));
        }

        HashMap<Long, Double> wsmMap = new HashMap<Long,Double>();
        HashMap<Long, Double> wpmMap = new HashMap<Long,Double>();

        System.out.println("WSM");
        wsmMap = WSM(q1Matrice);
        System.out.println(wsmMap.values());

        System.out.println("WPM");
        wpmMap = WPM(q2Matrice);
        System.out.println(wpmMap.values());

        HashMap<Long,Double> ranked = rankProviders(wsmMap,wpmMap);

        System.out.println("final rank: ");
        for (Long key : ranked.keySet()){
            System.out.println("id: " + key + " points: " + ranked.get(key));
        }

    }*/
}