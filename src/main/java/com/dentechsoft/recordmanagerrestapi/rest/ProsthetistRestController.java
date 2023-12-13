package com.dentechsoft.recordmanagerrestapi.rest;

import com.dentechsoft.recordmanagerrestapi.entity.Prosthetist;
import com.dentechsoft.recordmanagerrestapi.service.ProsthetistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProsthetistRestController {

    private ProsthetistService prosthetistService;

    @Autowired
    public ProsthetistRestController(ProsthetistService prosthetistService) {
        this.prosthetistService = prosthetistService;
    }

    // expose "/prosthetists" and return a list of prosthetists
    @GetMapping("/prosthetists")
    public List<Prosthetist> findAll(){
        return prosthetistService.findAll();
    }

    // add mapping for GET "/prosthetists/{prosthetistId}
    @GetMapping("/prosthetists/{prosthetistId}")
    public Prosthetist getProsthetist(@PathVariable int prosthetistId){

        Prosthetist prosthetist = prosthetistService.findById(prosthetistId);

        if(prosthetist == null){
            throw new RuntimeException("Prosthetist not found by id: " + prosthetistId);
        }

        return prosthetist;
    }

    // add mapping for POST "/prosthetists" - add new prosthetist
    @PostMapping(value = "/prosthetists")
    public Prosthetist addProsthetist(@RequestBody Prosthetist prosthetist){

        // set id to 0 to force to save new item instead of update
        prosthetist.setId(0L);

        Prosthetist dbProsthetist = prosthetistService.save(prosthetist);

        return dbProsthetist;
    }

    // add mapping for PUT "/prosthetists" - update existing prosthetist
    @PutMapping(value = "/prosthetists")
    public Prosthetist updateProsthetist(@RequestBody Prosthetist prosthetist){

        Prosthetist dbProsthetist = prosthetistService.save(prosthetist);

        return dbProsthetist;
    }

    // add mapping for DELETE "prosthetists/{prosthetistId}" - delete prosthetist
    @DeleteMapping("/prosthetists/{prosthetistId}")
    public String deleteProsthetist(@PathVariable int prosthetistId){

        Prosthetist prosthetist = prosthetistService.findById(prosthetistId);

        // throw exception if null
        if(prosthetist == null){
            throw new RuntimeException("Prosthetist not found by id: " + prosthetistId);
        }

        prosthetistService.deleteById(prosthetistId);

        return "Prosthetist by id: " + prosthetistId + " - DELETED";
    }
}
