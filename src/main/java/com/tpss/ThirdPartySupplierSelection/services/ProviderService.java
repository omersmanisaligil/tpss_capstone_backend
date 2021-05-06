package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.ProviderDAO;
import com.tpss.ThirdPartySupplierSelection.entity.*;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddProviderRequest;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddVehicleRequest;
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
    VehicleService vehicleService;

    @Autowired
    TechService techService;

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
        String responseMessage = "Operation: addProvider::\n ";
        String[] certNames = addProviderRequest.getCertNames();
        String[] productNames = addProviderRequest.getProductNames();
        //TODO write mapper class
        Provider provider = new Provider(
        addProviderRequest.getProviderName(),
        addProviderRequest.getProviderDesc(),
        addProviderRequest.getFoundationYear(),
        addProviderRequest.getNumberOfOrders(),
        addProviderRequest.getOperationArea()
        );

        if (certNames != null && certNames.length > 0) {
            for(String certName : certNames){
                if(certificateService.existsByCertName(certName.toUpperCase())) {
                    Certificate cert = certificateService.getOneByCertName(certName).get();

                    provider.insertCertificates(cert);
                }
                else{
                    responseMessage += " certificate " + certName +  " not recognized, it does not exist in the database\n" +
                    " if this is a valid certificate, please define it";
                }
            }
        }
        if (productNames != null && productNames.length > 0){
            for(String productName : productNames){
                if(productService.existsByProductName(productName.toUpperCase())){
                    Product product = productService.getOneByProductName(productName).get();

                    product.insertProvider(provider);
                }
                else{
                    responseMessage += " product " + productName + " not recognized, it does not exist in the database\n" +
                    " if this is a valid product, please define it";
                }
            }

        }

        providerDAO.save(provider);
    }

    public void insertOrder(Order order, Long providerID){
        Provider provider = providerDAO.getOne(providerID);

        provider.insertOrder(order);
    }

    public void insertVehicle(AddVehicleRequest vehicleRequest, Long providerID){
        Provider provider = providerDAO.getOne(providerID);

        Vehicle vehicle = vehicleService.addVehicle(vehicleRequest);

        provider.insertVehicle(vehicle);
    }

    public void insertProduct(Product product, Long providerID){
        Provider provider = providerDAO.getOne(providerID);

        provider.insertProduct(product);
    }
}
