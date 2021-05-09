package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.payload.request.MCDMFilterRequest;
import com.tpss.ThirdPartySupplierSelection.services.MCDMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mcdm")
public class MCDMController {

    @Autowired
    MCDMService mcdmService;

    @GetMapping("/filter")
    public ResponseEntity<Page<Provider>> filterProviders(@RequestParam(name="page", defaultValue="0") int page,
                                                          @RequestParam(name="size", defaultValue = "0") int size,
                                                          @RequestBody MCDMFilterRequest mcdmFilterRequest){
        mcdmService.filterProviders(mcdmFilterRequest,page,size);
        return null;
    }

    @GetMapping("/waspas")
    public ResponseEntity<Page<Provider>> applyWASPAS(@RequestParam(name="page", defaultValue="0") int page,
                                                      @RequestParam(name="size", defaultValue = "0") int size,
                                                      MCDMFilterRequest mcdmFilterRequest){
        Page<Provider> providersWASPAS = mcdmService.applyWASPAS(mcdmFilterRequest,page,size);
        return ResponseEntity.status(HttpStatus.OK).body(providersWASPAS);
    }

    @GetMapping("/topsis")
    public ResponseEntity<Page<Provider>> applyTOPSIS(@RequestParam(name="page", defaultValue="0") int page,
                                                      @RequestParam(name="size", defaultValue = "0") int size,
                                                      MCDMFilterRequest mcdmFilterRequest){
        Page<Provider> providersTOPSIS = mcdmService.applyTOPSIS(mcdmFilterRequest,page,size);
        return ResponseEntity.status(HttpStatus.OK).body(providersTOPSIS);
    }


}
