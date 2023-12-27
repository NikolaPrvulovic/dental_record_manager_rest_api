package com.dentechsoft.recordmanagerrestapi.rest;

import com.dentechsoft.recordmanagerrestapi.entity.Dentist;
import com.dentechsoft.recordmanagerrestapi.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DentistRestController {
    private DentistService dentistService;

    @Autowired
    public DentistRestController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    // expose "/dentists" and return a list of dentists
    @GetMapping("/dentists")
    public List<Dentist> findAll(){
        return dentistService.findAll();
    }

    // add mapping for GET "/dentists/{dentistId}
    @GetMapping("/dentists/{dentistId}")
    public Dentist getDentist(@PathVariable int dentistId){

        Dentist dentist = dentistService.findById(dentistId);

        if(dentist == null){
            throw new RuntimeException("Dentist not found by id: " + dentistId);
        }

        return dentist;
    }

    // add mapping for POST "/dentists" - add new dentist
    @PostMapping("/dentists")
    public Dentist addDentist(@RequestBody Dentist dentist){

        // set id to 0 to force to save new item instead of update
        dentist.setId(0L);

        Dentist dbDentist = dentistService.save(dentist);

        return dbDentist;
    }

    // add mapping for PUT "/dentists" - update existing dentist
    @PutMapping("/dentists")
    public Dentist updateDentist(@RequestBody Dentist dentist){

//        Dentist dbDentist = dentistService.findById(Math.toIntExact(dentist.getId()));
//
//        // if user adds or updates patient, but like updating dentist, so it does not set null to dentist fields
//        if(dentist.getPatients() != null) {
//            dbDentist.setPatients(dentist.getPatients());
//
//        // if user updates dentist by id
//        } else {
//            dbDentist.setId(dentist.getId());
//            dbDentist.setEmail(dentist.getEmail());
//            dbDentist.setFirstName(dentist.getFirstName());
//            dbDentist.setLastName(dentist.getLastName());
//            dbDentist.setPhoneNumber(dentist.getPhoneNumber());
//
//            dentistService.save(dbDentist);
//
//        }
        Dentist dbDentist = dentistService.save(dentist);

        return dbDentist;


    }

    // add mapping for DELETE "dentists/{dentistId}" - delete dentist
    @DeleteMapping("/dentists/{dentistId}")
    public String deleteDentist(@PathVariable int dentistId){

        Dentist dentist = dentistService.findById(dentistId);

        // throw exception if null
        if(dentist == null){
            throw new RuntimeException("Dentist not found by id: " + dentistId);
        }

        dentistService.deleteById(dentistId);

        return "Dentist by id: " + dentistId + " - DELETED";
    }
}
