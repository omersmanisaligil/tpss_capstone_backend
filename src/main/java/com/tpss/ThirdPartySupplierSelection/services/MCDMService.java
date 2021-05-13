package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.ProductDAO;
import com.tpss.ThirdPartySupplierSelection.dao.ProviderDAO;
import com.tpss.ThirdPartySupplierSelection.dao.ProviderDAOImpl;
import com.tpss.ThirdPartySupplierSelection.entity.Product;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.payload.request.ProviderFilterRequest;
import com.tpss.ThirdPartySupplierSelection.payload.request.ProviderOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MCDMService {

    @Autowired
    ProviderDAO providerDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ProviderDAOImpl providerDAOImpl;

    public Page<Provider> filterProviders(ProviderFilterRequest providerFilterRequest, int page, int size){
        Pageable pageRequest = PageRequest.of(page,size);
        //TODO from json to hashmap
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("operationArea", providerFilterRequest.getOperationArea());
        filters.put("productName",providerFilterRequest.getProductName());
        filters.put("greenPercentage",providerFilterRequest.getGreenPercentage());
        filters.put("certs",providerFilterRequest.getCerts());

        List<Provider> filteredProviders = providerDAOImpl.filterData(filters);
        PageImpl<Provider> filteredProvidersPage = new PageImpl<Provider>(filteredProviders,pageRequest, filteredProviders.size());
        return filteredProvidersPage;
    }

    public Page<Provider> applyTOPSIS(ProviderOrderRequest providerOrderRequest, int page, int size){
        Pageable pageRequest = PageRequest.of(page,size);

        return null;
    }

    public Page<Provider> applyWASPAS(ProviderOrderRequest providerFilterRequest,int page,int size){
        Pageable pageRequest = PageRequest.of(page,size);

        return null;
    }

}