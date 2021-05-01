package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.entity.Tech;
import com.tpss.ThirdPartySupplierSelection.services.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tech")
public class TechController {

    @Autowired
    TechService techService;

    public TechController(TechService techService){
        this.techService = techService;
    }

    @GetMapping(path="")
    public ResponseEntity<Page<Tech>> getAll(
        @RequestParam(name="page", defaultValue = "0") int page,
        @RequestParam(name="size", defaultValue = "3") int size
    //,@RequestParam(name="sort", defaultValue = "id") String[] sort
    ){
        Page<Tech> allTech = techService.getAll(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(allTech);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Tech> getOneByID(@PathVariable("id") Long id){
        Tech tech = techService.getOneByID(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(tech);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addTech(@Validated @NonNull @RequestBody Tech tech){
        techService.addTech(tech);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<Tech> updateTech(@Validated @NonNull @RequestBody Tech tech,
                                           @PathVariable("id") Long id){

        Tech updatedTech = techService.updateTech(tech,id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTech);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteTech(@PathVariable("id") Long id){
        techService.deleteTech(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
