package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.TechDAO;
import com.tpss.ThirdPartySupplierSelection.entity.Tech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechService {

    @Autowired
    TechDAO techDAO;

    public Page<Tech> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Tech> techs= techDAO.findAll(pageRequest);

        return techs;
    }

    public Optional<Tech> getOneByID(Long id){
    Optional<Tech> tech = techDAO.findById(id);

        return tech;
    }

    public Tech updateTech(Tech tech, Long id){
        Tech techToUpdate = null;

        techToUpdate=tech;
        tech.setTechId(id);

        techDAO.save(techToUpdate);

        return techToUpdate;
    }

    public void deleteTech(Long id){
        techDAO.deleteById(id);
    }

    public void addTech(Tech tech){
        techDAO.save(tech);
    }
}
