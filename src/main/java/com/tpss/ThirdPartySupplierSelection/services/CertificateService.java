package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.CertificateDAO;
import com.tpss.ThirdPartySupplierSelection.entity.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertificateService extends GenericService{

    @Autowired
    CertificateDAO certificateDAO;

    public Page<Certificate> getAll(int page, int size){
	Pageable pageRequest = PageRequest.of(page, size);
	Page<Certificate> certificates= certificateDAO.findAll(pageRequest);

	return certificates;
    }

    public Optional<Certificate> getOneByID(Long id){
	Optional<Certificate> certificate= certificateDAO.findById(id);

	return certificate;
    }

    public Certificate updateCertificate(Certificate certificate, Long id){
	Certificate existingCertificate = certificateDAO.findById(id).get();

	if(existingCertificate != null && certificate != null && certificate.getCertName().length()>0) {
	    existingCertificate = certificate;
	    existingCertificate.setCertId(id);

	    certificateDAO.save(existingCertificate);
	}
	return existingCertificate;
    }

    public void deleteCertificate(Long id){
	certificateDAO.deleteById(id);
    }

    public void addCertificate(Certificate certificate){
	    certificateDAO.save(certificate);
    }
}
