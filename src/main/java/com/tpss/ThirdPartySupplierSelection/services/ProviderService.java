package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.ProviderDAO;
import com.tpss.ThirdPartySupplierSelection.dto.DTOMapper;
import com.tpss.ThirdPartySupplierSelection.dto.ProviderDTO;
import com.tpss.ThirdPartySupplierSelection.entity.*;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddProviderRequest;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddVehicleRequest;
import com.tpss.ThirdPartySupplierSelection.util.PageImplCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProviderService{

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

    public PageImpl<ProviderDTO> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        List<Provider> providers = providerDAO.findAll();

        List<ProviderDTO> providerDTOList = DTOMapper.toProviderDTOList(providers);

        PageImpl<ProviderDTO> providerDTOPage = PageImplCustom.createPage(
                                                providerDTOList,
                                                pageRequest);
        return providerDTOPage;
    }

    public ProviderDTO getOneByID(Long id){
        Provider provider = providerDAO.findById(id).get();
        ProviderDTO providerDTO = null;

        if(provider!=null)
             providerDTO = DTOMapper.toProviderDTO(provider);

        return providerDTO;
    }

    public PageImpl<ProviderDTO> getByOperationArea(String operationArea, int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        List<ProviderDTO> providerDTOList = new ArrayList<>();
        List<Provider> providersOfArea = providerDAO.findByOperationArea(operationArea);

        providerDTOList = DTOMapper.toProviderDTOList(providersOfArea);

        PageImpl<ProviderDTO> providerDTOPage = PageImplCustom.createPage(
                                                providerDTOList,
                                                pageable);

        return providerDTOPage;
    }

    public PageImpl<ProviderDTO> searchByName(int page, int size, String name){
        Pageable pageable = PageRequest.of(page,size);
        List<ProviderDTO> providerDTOList = new ArrayList<>();
        List<Provider> providerList = providerDAO.searchByProviderName(name);

        providerDTOList = DTOMapper.toProviderDTOList(providerList);

        PageImpl<ProviderDTO> providerDTOPage = PageImplCustom.createPage(
                                                providerDTOList,
                                                pageable);

        return providerDTOPage;
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
        addProviderRequest.getOperationArea()
        );

        if (certNames != null && certNames.length > 0) {
            for(String certName : certNames){
                if(certificateService.existsByCertName(certName.toUpperCase())) {
                    Certificate cert = certificateService.getOneByCertName(certName).get();

                    provider.insertCertificate(cert);
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

    public boolean insertProduct(Product product, Long providerID){
        Provider provider = providerDAO.getOne(providerID);
        boolean productExists = productService.addProduct(product);
        provider.insertProduct(product);
        return productExists;
    }

    public boolean insertCert(Certificate cert, Long providerID){
        Provider provider = providerDAO.getOne(providerID);
        boolean certExists = certificateService.existsByCertName(cert.getCertName());

        if(certExists)
            provider.insertCertificate(cert);

        return certExists;
    }

}
