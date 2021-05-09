package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.ProductDAO;
import com.tpss.ThirdPartySupplierSelection.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public Page<Product> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Product> products= productDAO.findAll(pageRequest);

        return products;
    }

    public Optional<Product> getOneByID(Long id){
        Optional<Product> product = productDAO.findById(id);

        return product;
    }

    public Optional<Product> getOneByProductName(String productName){
        Optional<Product> product = productDAO.findByProductName(productName);

        return product;
    }

    public Page<Product> searchByName(int page, int size, String name){
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products = productDAO.searchByProductName(name,pageable);

        return products;
    }

    public boolean existsByProductName(String productName){
        boolean exists = productDAO.existsByProductName(productName);

        return exists;
    }

    public Product updateProduct(Product product, Long id){
        Product productToUpdate = null;

        productToUpdate=product;
        product.setProductId(id);

        productDAO.save(productToUpdate);

        return productToUpdate;
    }

    public void deleteProduct(Long id){
        productDAO.deleteById(id);
    }

    public void addProduct(Product product){
        product.setProductName(product.getProductName().toUpperCase());
        productDAO.save(product);
    }
}
