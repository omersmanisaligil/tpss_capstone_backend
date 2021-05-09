package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.payload.request.MCDMFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class MCDMService {

    public Page<Provider> filterProviders(MCDMFilterRequest mcdmFilterRequest,int page,int size){
        Pageable pageRequest = PageRequest.of(page,size);
        return null;
    }

    public Page<Provider> applyTOPSIS(MCDMFilterRequest mcdmFilterRequest,int page,int size){
        Pageable pageRequest = PageRequest.of(page,size);

        return null;
    }

    public Page<Provider> applyWASPAS(MCDMFilterRequest mcdmFilterRequest,int page,int size){
        Pageable pageRequest = PageRequest.of(page,size);

        return null;
    }

}
