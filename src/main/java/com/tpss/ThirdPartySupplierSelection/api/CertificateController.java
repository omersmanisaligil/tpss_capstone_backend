package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.dto.CertificateDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Certificate;
import com.tpss.ThirdPartySupplierSelection.payload.response.MessageResponse;
import com.tpss.ThirdPartySupplierSelection.services.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    public CertificateController(CertificateService certificateService){
	this.certificateService = certificateService;
    }

    @GetMapping(path="")
    public ResponseEntity<Page<CertificateDTO>> getAll(
    @RequestParam(name="page", defaultValue = "0") int page,
    @RequestParam(name="size", defaultValue = "3") int size
    //,@RequestParam(name="sort", defaultValue = "id") String[] sort
    ){
	Page<CertificateDTO> allCertificates = certificateService.getAll(page,size);
	return ResponseEntity.status(HttpStatus.OK).body(allCertificates);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<CertificateDTO> getOneByID(@PathVariable("id") Long id){
	CertificateDTO certificate = certificateService.getOneByID(id);
	return ResponseEntity.status(HttpStatus.OK).body(certificate);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addCertificate(@Validated @NonNull @RequestBody Certificate certificate){
	boolean certExists = certificateService.addCertificate(certificate);

	HttpStatus status;
	MessageResponse msg = new MessageResponse();
	if(certExists){
	    status = HttpStatus.CONFLICT;
	    msg.setMessage("Certificate with name " + certificate.getCertName() + "already exists");
	}
	else{
	    status = HttpStatus.CREATED;
	    msg.setMessage("Certificate created successfully");
	}
	return ResponseEntity.status(status).body(msg);
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<Certificate> updateCertificate(@Validated @NonNull @RequestBody Certificate certificate,
					   @PathVariable("id") Long id){

	Certificate updatedCertificate = certificateService.updateCertificate(certificate,id);
	return ResponseEntity.status(HttpStatus.OK).body(updatedCertificate);
    }


    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteCertificate(@PathVariable("id") Long id){
	certificateService.deleteCertificate(id);
	return ResponseEntity.status(HttpStatus.OK).build();
    }
}
