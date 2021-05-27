package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
public class ProviderDAOImpl implements ProviderDAOCustom{
    @PersistenceContext
    EntityManager entityManager;

    private  String queryPt1 = "SELECT provider.provider_id,provider_name,provider_desc,foundation_year," +
    " operation_area" +
    " FROM PROVIDER provider";
    private  String queryPt3 =" (SELECT pc.provider_id" +
    " FROM CERTIFICATE cr" +
    " INNER JOIN CERT_PROVIDER_MAP pc ON cr.CERT_ID=pc.CERT_ID" +
    " WHERE cert_name in (?";
    private  String addCert =",?";
    private  String closeParanthesis = ") ";
    private  String queryPt4 = "group by pc.provider_id having count(*)=?) cp on " +
    " cp.provider_id = provider.provider_id ";
    private  String innerJoin = " INNER JOIN ";


    @Override
    public List<Provider> filterData(HashMap<String, Object> filters) {
	String queryPt2 = " (SELECT pr.PRODUCT_ID,pr.PRODUCT_NAME,pr.TEMP_IDEAL,pr.temp_unit,pr.humidity_ideal,pr.humidity_unit, pm.provider_id" +
	" FROM PRODUCT pr" +
	" INNER JOIN PROVIDER_PRODUCT_MAP pm ON pr.PRODUCT_ID=pm.PRODUCT_ID" +
	" WHERE pr.product_name=?) pd ON provider.provider_id=pd.provider_id ";
	String queryPt5 = " WHERE provider.operation_area=? ";

        String productName = (String)filters.get("productName");
	String operationArea = (String)filters.get("operationArea");
	List<String> certs = (List<String>)filters.get("certs");
	ArrayList<Object> params = new ArrayList<>();

	StringBuilder sb = new StringBuilder(queryPt1);
	sb.append(innerJoin);

	//not proud
	if(productName == null || productName == "") queryPt2 = queryPt2.replace("?","pr.product_name");
	else params.add(productName);

	sb.append(queryPt2);

	if(certs != null && certs.size()>0){
	    int certCount = ((List<String>)filters.get("certs")).size();
	    sb.append(innerJoin);
	    sb.append(queryPt3);
	    params.add(certs.get(0));
	    for (int i = 1; i < certs.size(); i++) {
		sb.append(addCert);
		params.add(certs.get(i));
	    }
	    sb.append(closeParanthesis);
	    sb.append(queryPt4);
	    params.add(certCount);
	}

	if(operationArea == null || operationArea == "") queryPt5 = queryPt5.replace("?","provider.operation_area");
	else params.add(operationArea);

	sb.append(queryPt5);

	System.out.println(sb.toString());
	Query q = entityManager.createNativeQuery(sb.toString(), Provider.class);

	for(int i = 0; i<params.size();i++){
	    q.setParameter(i+1, params.get(i));
	}
	List<Provider> result = q.getResultList();
	return result;
    }

    public List<Provider> filterDataForOrders(HashMap<String, Object> filters) {
        List<Provider> filteredProviders = filterData(filters);


        return filteredProviders;
    }
}