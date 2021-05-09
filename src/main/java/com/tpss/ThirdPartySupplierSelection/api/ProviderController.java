package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.entity.Order;
import com.tpss.ThirdPartySupplierSelection.entity.Product;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddProviderRequest;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddVehicleRequest;
import com.tpss.ThirdPartySupplierSelection.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Provider>> getAll(
    @RequestParam(name="page", defaultValue = "0") int page,
    @RequestParam(name="size", defaultValue = "3") int size
    //,@RequestParam(name="sort", defaultValue = "id") String[] sort
    ){
	Page<Provider> allProviders = providerService.getAll(page,size);
	return ResponseEntity.status(HttpStatus.OK).body(allProviders);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Provider> getOneByID(@PathVariable("id") Long id){
	Provider provider = providerService.getOneByID(id).get();
	Link link = linkTo(ProviderController.class).slash(provider.getProviderId()).withSelfRel();
	return ResponseEntity.status(HttpStatus.OK).body(provider);
    }

    @GetMapping(path="/filter")
    public ResponseEntity<Page<Provider>> getByOperationArea(@RequestParam(name="operationArea") String operationArea,
							     @RequestParam(name="page", defaultValue="0") int page,
							     @RequestParam(name="size", defaultValue="3") int size){
	Page<Provider> providersOfArea = providerService.getByOperationArea(operationArea,page,size);
        return ResponseEntity.status(HttpStatus.OK).body(providersOfArea);
    }

    @GetMapping(path="/search/")
    public ResponseEntity<Page<Provider>> SearchByProviderName(@RequestParam(name="providerName") String providerName,
							       @RequestParam(name="page", defaultValue="0") int page,
							       @RequestParam(name="size", defaultValue = "3") int size){
        Page<Provider> providersWithName = providerService.searchByName(page,size,providerName);
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

    @PostMapping(path="{id}/insertVehicle")
    public ResponseEntity<?> insertVehicle(@Validated @NonNull @RequestBody AddVehicleRequest addVehicleRequest,
					   @PathVariable("id") Long providerID){
	providerService.insertVehicle(addVehicleRequest,providerID);
	return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(path="{id}/insertProduct")
    public ResponseEntity<?> insertProduct(@Validated @NonNull @RequestBody Product product,
    					   @PathVariable("id") Long id){
        providerService.insertProduct(product,id);

        return ResponseEntity.status(HttpStatus.OK).build();
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
