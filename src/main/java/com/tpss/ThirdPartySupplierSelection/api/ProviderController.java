package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.dto.ProviderDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Certificate;
import com.tpss.ThirdPartySupplierSelection.entity.Order;
import com.tpss.ThirdPartySupplierSelection.entity.Product;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddProviderRequest;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddVehicleRequest;
import com.tpss.ThirdPartySupplierSelection.payload.response.MessageResponse;
import com.tpss.ThirdPartySupplierSelection.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    public ProviderController(ProviderService providerService){
	this.providerService = providerService;
    }

    @GetMapping(path="")
    public ResponseEntity<PageImpl<ProviderDTO>> getAll(
    @RequestParam(name="page", defaultValue = "0") int page,
    @RequestParam(name="size", defaultValue = "3") int size
    //,@RequestParam(name="sort", defaultValue = "id") String[] sort
    ){
	PageImpl<ProviderDTO> allProviders = providerService.getAll(page,size);
	return ResponseEntity.status(HttpStatus.OK).body(allProviders);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<ProviderDTO> getOneByID(@PathVariable("id") Long id){
	ProviderDTO provider = providerService.getOneByID(id);
	Link link = linkTo(ProviderController.class).slash(provider.getProviderId()).withSelfRel();
	return ResponseEntity.status(HttpStatus.OK).body(provider);
    }

    @GetMapping(path="/filter")
    public ResponseEntity<PageImpl<ProviderDTO>> getByOperationArea(@RequestParam(name="operationArea") String operationArea,
							     @RequestParam(name="page", defaultValue="0") int page,
							     @RequestParam(name="size", defaultValue="3") int size){
	PageImpl<ProviderDTO> providersOfArea = providerService.getByOperationArea(operationArea,page,size);
        return ResponseEntity.status(HttpStatus.OK).body(providersOfArea);
    }

    @GetMapping(path="/search/")
    public ResponseEntity<PageImpl<ProviderDTO>> SearchByProviderName(@RequestParam(name="providerName") String providerName,
								   @RequestParam(name="page", defaultValue="0") int page,
								   @RequestParam(name="size", defaultValue = "3") int size){
        PageImpl<ProviderDTO> providersWithName = providerService.searchByName(page,size,providerName);
        return ResponseEntity.status(HttpStatus.OK).body(providersWithName);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addProvider(@Validated @NonNull @RequestBody AddProviderRequest addProviderRequest){
	providerService.addProvider(addProviderRequest);
	return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping(path="{id}/insertOrder")
    public ResponseEntity<?> insertOrder(@Validated @NonNull @RequestBody Order order,
					 @PathVariable("id") Long providerID){
	providerService.insertOrder(order, providerID);
	return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(path="{id}/insertCert")
    public ResponseEntity<?> insertCert(@Validated @NonNull @RequestBody Certificate cert,
    					@PathVariable("id") Long providerID){
  	boolean certExist = providerService.insertCert(cert,providerID);
        HttpStatus status;
        MessageResponse msg = new MessageResponse();

        if(certExist){
            status = HttpStatus.OK;
            msg.setMessage("Certificate added to the provider");
	}
        else{
            status = HttpStatus.CONFLICT;
            msg.setMessage("Certificate does not exist in the database, please define it first");
	}
        return ResponseEntity.status(status).body(msg);
    }

    @PostMapping(path="{id}/insertVehicle")
    public ResponseEntity<?> insertVehicle(@Validated @NonNull @RequestBody AddVehicleRequest addVehicleRequest,
					   @PathVariable("id") Long providerID){
	providerService.insertVehicle(addVehicleRequest,providerID);
	return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(path="{id}/insertProduct")
    public ResponseEntity<?> insertProduct(@Validated @NonNull @RequestBody Product product,
    					   @PathVariable("id") Long id){
        boolean productExists = providerService.insertProduct(product,id);
	MessageResponse msg = new MessageResponse();
	HttpStatus status;
        if(productExists){
            msg.setMessage("Product added to the provider's catalog");
            status = HttpStatus.OK;
        }
        else{
            msg.setMessage("Product did not exist, it's created and inserted to the provider's catalog");
            status = HttpStatus.CREATED;
        }
        return ResponseEntity.status(status).body(msg);
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<Provider> updateProvider(@Validated @NonNull @RequestBody Provider provider,
					   @PathVariable("id") Long id){

	Provider updatedProvider = providerService.updateProvider(provider,id);
	return ResponseEntity.status(HttpStatus.OK).body(updatedProvider);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteProvider(@PathVariable("id") Long id){
	providerService.deleteProvider(id);
	return ResponseEntity.status(HttpStatus.OK).build();
    }
}
