package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.ProductDAO;
import com.tpss.ThirdPartySupplierSelection.dao.ProviderDAO;
import com.tpss.ThirdPartySupplierSelection.dao.ProviderDAOImpl;
import com.tpss.ThirdPartySupplierSelection.dto.DTOMapper;
import com.tpss.ThirdPartySupplierSelection.dto.ProviderDTO;
import com.tpss.ThirdPartySupplierSelection.dto.WaspasDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Product;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.mcdm.waspas.WaspasImpl;
import com.tpss.ThirdPartySupplierSelection.payload.request.ProviderFilterRequest;
import com.tpss.ThirdPartySupplierSelection.payload.request.ProviderOrderRequest;
import com.tpss.ThirdPartySupplierSelection.util.PageImplCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Page<ProviderDTO> filterProviders(ProviderFilterRequest providerFilterRequest, int page, int size){
        Pageable pageRequest = PageRequest.of(page,size);
        //TODO from json to hashmap
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("operationArea", providerFilterRequest.getOperationArea());
        filters.put("productName",providerFilterRequest.getProductName());
        filters.put("greenPercentage",providerFilterRequest.getGreenPercentage());
        filters.put("certs",providerFilterRequest.getCerts());

        List<ProviderDTO> filteredProvidersDTO = providerDAOImpl.filterData(filters);
        Page<ProviderDTO> filteredProvidersPage = PageImplCustom.createPage(
                                                    filteredProvidersDTO,
                                                    pageRequest);
        return filteredProvidersPage;
    }

    public WaspasDTO applyWASPAS(ProviderOrderRequest providerOrderRequest) {

        HashMap<String, Object> filters = new HashMap<>();
        filters.put("operationArea", providerOrderRequest.getOperationArea());
        filters.put("productName", providerOrderRequest.getProductName());
        filters.put("greenPercentage", providerOrderRequest.getGreenPercentage());
        filters.put("certs", providerOrderRequest.getCerts());
        filters.put("deliveryLocation", providerOrderRequest.getDeliveryLocation());
        filters.put("arrivalDate", providerOrderRequest.getArrivalDate());
        filters.put("orderDate", providerOrderRequest.getOrderDate());
        filters.put("amount", providerOrderRequest.getAmount());
        filters.put("unit", providerOrderRequest.getUnit());

        List<ProviderDTO> providerDTOs = providerDAOImpl.filterDataForOrders(filters);
        //System.out.println("MCDMService.applyWASPAS: providers: " + Arrays.toString(providers.toArray()));
        WaspasDTO waspasDTO = WaspasImpl.execute(providerDTOs);

        return waspasDTO;
    }
}
