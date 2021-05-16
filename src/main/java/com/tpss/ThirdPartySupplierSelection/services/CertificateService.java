package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.CertificateDAO;
import com.tpss.ThirdPartySupplierSelection.dto.CertificateDTO;
import com.tpss.ThirdPartySupplierSelection.dto.DTOMapper;
import com.tpss.ThirdPartySupplierSelection.entity.Certificate;
import com.tpss.ThirdPartySupplierSelection.util.PageImplCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {

    @Autowired
    private CertificateDAO certificateDAO;

    public Page<CertificateDTO> getAll(int page, int size){
	Pageable pageRequest = PageRequest.of(page, size);
	List<Certificate> certificates = certificateDAO.findAll();

	List<CertificateDTO> certDTOList = DTOMapper.toCertificateDTOList(certificates);

	PageImpl<CertificateDTO> certDTOPage = PageImplCustom.createPage(
						certDTOList,
						pageRequest);


	return certDTOPage;
    }

    public CertificateDTO getOneByID(Long id){
	Optional<Certificate> certificate = certificateDAO.findById(id);
	CertificateDTO certDTO = DTOMapper.toCertDTO(certificate.get());
	return certDTO;
    }

    public boolean existsByCertName(String certName){
        boolean exists = certificateDAO.existsByCertName(certName);

        return exists;
    }

    public Optional<Certificate> getOneByCertName(String certName){
        Optional<Certificate> certificate = certificateDAO.findByCertName(certName);

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

    public boolean addCertificate(Certificate certificate) {
	boolean certExists = certificateDAO.existsByCertName(certificate.getCertName());
	if (!certExists){
	    certificate.setCertName(certificate.getCertName().toUpperCase());
	    certificateDAO.save(certificate);
    	}
        return certExists;
    }
}
