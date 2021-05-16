package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.ProductDAO;
import com.tpss.ThirdPartySupplierSelection.dto.DTOMapper;
import com.tpss.ThirdPartySupplierSelection.dto.ProductDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Product;
import com.tpss.ThirdPartySupplierSelection.util.PageImplCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public Page<ProductDTO> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        List<Product> products= productDAO.findAll();

        List<ProductDTO> productDTOs = DTOMapper.toProductDTOList(products);
        Page<ProductDTO> productDTOPage = PageImplCustom.createPage(productDTOs,pageRequest);

        return productDTOPage;
    }

    public ProductDTO getOneByID(Long id){
        Product product = productDAO.findById(id).get();

        ProductDTO productDTO = DTOMapper.toProductDTO(product);

        return productDTO;
    }

    public Product getOneByProductName(String productName){
        Product product = productDAO.findByProductName(productName).get();
        return product;
    }

    public Page<ProductDTO> searchByName(int page, int size, String name){
        Pageable pageable = PageRequest.of(page,size);
        List<Product> products = productDAO.searchByProductName(name);

        List<ProductDTO> productDTOs = DTOMapper.toProductDTOList(products);
        Page<ProductDTO> productDTOPage = PageImplCustom.createPage(productDTOs,pageable);

        return productDTOPage;
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

    public boolean addProduct(Product product){
        if(!existsByProductName(product.getProductName())){
            product.setProductName(product.getProductName().toUpperCase());
            productDAO.save(product);
            return true;
        }
        return false;
    }
}
