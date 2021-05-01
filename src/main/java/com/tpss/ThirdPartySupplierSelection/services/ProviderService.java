package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.ProviderDAO;
import com.tpss.ThirdPartySupplierSelection.entity.*;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddProviderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService extends GenericService{

    @Autowired
    ProviderDAO providerDAO;

    @Autowired
    CertificateService certificateService;

    @Autowired
    ProductService productService;

    public Page<Provider> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Provider> providers= providerDAO.findAll(pageRequest);

        return providers;
    }

    public Optional<Provider> getOneByID(Long id){
        Optional<Provider> provider = providerDAO.findById(id);

        return provider;
    }

    public Provider updateProvider(Provider provider, Long id){
        Provider providerToUpdate = null;

        providerToUpdate=provider;
        provider.setProviderId(id);

        providerDAO.save(providerToUpdate);

        return providerToUpdate;
    }

    public void deleteProvider(Long id){
        providerDAO.deleteById(id);
    }

    public void addProvider(AddProviderRequest addProviderRequest) {
        String certName = addProviderRequest.getCertName();
        String productName = addProviderRequest.getProductName();
        //TODO write mapper class
        Provider provider = new Provider(
        addProviderRequest.getProviderName(),
        addProviderRequest.getProviderDesc(),
        addProviderRequest.getFoundationYear(),
        addProviderRequest.getNumberOfOrders(),
        addProviderRequest.getOperationArea()
        );

        if (certName != null && certName.length() > 0) {
            Certificate cert=new Certificate(certName);

            certificateService.addCertificate(cert);
            provider.insertCertificates(cert);
        }
        if (productName != null && productName.length() > 0){
            Product product=new Product(productName,
            addProviderRequest.getIdealTemp(),
            addProviderRequest.getTempUnit(),
            addProviderRequest.getIdealHumidity(),
            addProviderRequest.getHumidityUnit());

            productService.addProduct(product);
            product.insertProvider(provider);
        }

        providerDAO.save(provider);
    }

    public void insertOrder(Order order, Long providerID){
        Provider provider = providerDAO.getOne(providerID);

        provider.insertOrder(order);
    }

    public void insertVehicle(Vehicle vehicle, Long providerID){
        Provider provider = providerDAO.getOne(providerID);

        provider.insertVehicle(vehicle);
    }

    public void insertProduct(Product product, Long providerID){
        Provider provider = providerDAO.getOne(providerID);

        provider.insertProduct(product);
    }
}
