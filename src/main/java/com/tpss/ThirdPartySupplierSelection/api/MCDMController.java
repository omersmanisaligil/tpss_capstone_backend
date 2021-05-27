package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.dto.ProviderDTO;
import com.tpss.ThirdPartySupplierSelection.dto.WaspasDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.payload.request.ProviderFilterRequest;
import com.tpss.ThirdPartySupplierSelection.payload.request.ProviderOrderRequest;
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

    @PostMapping("/filter")
    public ResponseEntity<Page<ProviderDTO>> filterProviders(@RequestParam(name="page", defaultValue="0") int page,
                                                             @RequestParam(name="size", defaultValue = "3") int size,
                                                             @RequestBody ProviderFilterRequest providerFilterRequest){
        Page<ProviderDTO> providers = mcdmService.filterProviders(providerFilterRequest,page,size);
        return ResponseEntity.status(HttpStatus.OK).body(providers);
    }

    @GetMapping("/waspas")
    public ResponseEntity<WaspasDTO> applyWASPAS(ProviderOrderRequest providerOrderRequest){
        WaspasDTO waspasResults = mcdmService.applyWASPAS(providerOrderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(waspasResults);
    }

    //@GetMapping("/topsis")
    //public ResponseEntity<Page<ProviderDTO>> applyTOPSIS(@RequestParam(name="page", defaultValue="0") int page,
    //                                                  @RequestParam(name="size", defaultValue = "0") int size,
    //                                                  ProviderOrderRequest providerOrderRequest){
    //    Page<ProviderDTO> providersTOPSIS = mcdmService.applyTOPSIS(providerOrderRequest,page,size);
    //    return ResponseEntity.status(HttpStatus.OK).body(providersTOPSIS);
    //}
}
