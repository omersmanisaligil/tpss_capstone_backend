package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.entity.Product;
import com.tpss.ThirdPartySupplierSelection.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(ProductService productService){
	this.productService = productService;
    }

    @GetMapping(path="")
    public ResponseEntity<Page<Product>> getAll(@RequestParam(name="page", defaultValue = "0") int page,
						@RequestParam(name="size", defaultValue = "3") int size
						/*,@RequestParam(name="sort", defaultValue = "id") String[] sort*/){
	Page<Product> allProducts = productService.getAll(page,size);
	return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Product> getOneByID(@PathVariable("id") Long id){
	Product product = productService.getOneByID(id).get();
	return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping(path="/search/")
    public ResponseEntity<Page<Product>> searchByName(@RequestParam(name="productName") String productName,
    						      @RequestParam(name="page", defaultValue = "0") int page,
						      @RequestParam(name="size", defaultValue = "3") int size){
        Page<Product> products = productService.searchByName(page,size,productName);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addProduct(@Validated @NonNull @RequestBody Product product){
	productService.addProduct(product);
	return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<Product> updateProduct(@Validated @NonNull @RequestBody Product product,
					   @PathVariable("id") Long id){

	Product updatedProduct = productService.updateProduct(product,id);
	return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
	productService.deleteProduct(id);
	return ResponseEntity.status(HttpStatus.OK).build();
    }
}
