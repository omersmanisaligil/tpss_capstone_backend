package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.lang.reflect.Array;
import java.util.*;

@Repository
public class ProviderDAOImpl implements ProviderDAOCustom{
    @PersistenceContext
    EntityManager entityManager;

    private final String queryPt1 = "SELECT provider.provider_id,provider_name,provider_desc,foundation_year," +
    "operation_area, number_of_orders," +
    "product_id, product_name, temp_ideal, temp_unit, humidity_ideal, humidity_unit" +
    " FROM PROVIDER provider";
    private final String queryPt2 = " (SELECT pr.PRODUCT_ID,pr.PRODUCT_NAME,pr.TEMP_IDEAL,pr.temp_unit,pr.humidity_ideal,pr.humidity_unit, pm.provider_id" +
    " FROM PRODUCT pr" +
    " INNER JOIN PROVIDER_PRODUCT_MAP pm ON pr.PRODUCT_ID=pm.PRODUCT_ID" +
    " WHERE pr.product_name=?) pd ON provider.provider_id=pd.provider_id";
    private final String queryPt3 =" (SELECT pc.provider_id" +
    " FROM CERTIFICATE cr" +
    " INNER JOIN CERT_PROVIDER_MAP pc ON cr.CERT_ID=pc.CERT_ID" +
    " WHERE cert_name in (?";
    private final String addCert =",?";
    private final String closeParanthesis = ") ";
    private final String queryPt4 = "group by pc.provider_id having count(*)=?) cp on" +
    " cp.provider_id = provider.provider_id ";
    private final String queryPt5 = "WHERE provider.operation_area=?";
    private final String innerJoin = " INNER JOIN ";


    @Override
    public List<Provider> filterData(HashMap<String, Object> filters) {
	String productName = (String)filters.get("productName");
	String operationArea = (String)filters.get("operationArea");
	List<String> certs = (List<String>)filters.get("certs");
	ArrayList<Object> params = new ArrayList<>();
        /*SELECT provider.provider_id, provider_name, provider_desc, foundation_year, operation_area, number_of_orders, product_id, product_name, temp_ideal, temp_unit, humidity_ideal, humidity_unit
	FROM PROVIDER provider
	INNER JOIN
	(SELECT pr.PRODUCT_ID,pr.PRODUCT_NAME,pr.TEMP_IDEAL,pr.temp_unit,pr.humidity_ideal,pr.humidity_unit, pm.provider_id
	 	FROM PRODUCT pr
	 		INNER JOIN PROVIDER_PRODUCT_MAP pm ON pr.PRODUCT_ID=pm.PRODUCT_ID
	 	WHERE pr.product_name='elma') pd ON provider.provider_id=pd.provider_id
	INNER JOIN
	(SELECT pc.provider_id
	 	FROM CERTIFICATE cr
	 		INNER JOIN CERT_PROVIDER_MAP pc ON cr.CERT_ID=pc.CERT_ID
	 	WHERE cert_name in ('ISO111','ISO9320')
	 		group by pc.provider_id having count(*)=2) cp on
	cp.provider_id = provider.provider_id

	WHERE provider.operation_area='İstanbul'*/
	int certCount = ((List<String>)filters.get("certs")).size();
	StringBuilder sb = new StringBuilder(queryPt1);

	if(productName != null && productName != ""){
	    sb.append(innerJoin);
	    sb.append(queryPt2);
	    params.add(productName);
	}

	if(certs != null && certs.size()>0){
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

	if(operationArea != null && operationArea != ""){
	    sb.append(queryPt5);
	    params.add(operationArea);
	}

	Query q = entityManager.createNativeQuery(sb.toString());

	for(int i = 0; i<params.size();i++){
	   q.setParameter(i+1, params.get(i));
	}

	List result = q.getResultList();
	return result;
    }

}