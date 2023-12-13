package com.dentechsoft.recordmanagerrestapi.rest;

import com.dentechsoft.recordmanagerrestapi.entity.Patient;
import com.dentechsoft.recordmanagerrestapi.enums.ProstheticStatus;
import com.dentechsoft.recordmanagerrestapi.enums.RestorationType;
import com.dentechsoft.recordmanagerrestapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientRestController {
    private PatientService patientService;

    @Autowired
    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
    }

    // expose "/patients" and return a list of patients
    @GetMapping("/patients")
    public List<Patient> findAll(){
        return patientService.findAll();
    }

    // add mapping for GET "/patients/{patientId}
    @GetMapping("/patients/{patientId}")
    public Patient getPatient(@PathVariable int patientId){

        Patient patient = patientService.findById(patientId);

        if(patient == null){
            throw new RuntimeException("Patient not found by id: " + patientId);
        }

        return patient;
    }

    // add mapping for POST "/patients" - add new patient
    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient) {


        // set id to 0 to force to save new item instead of update
        patient.setId(0L);

        return patientService.save(patient);
    }

    // add mapping for PUT "/patients" - update existing patient
    @PutMapping("/patients")
    public Patient updatePatient(@RequestBody Patient patient){

        return patientService.save(patient);
    }

    // add mapping for DELETE "patients/{patientId}" - delete patient
    @DeleteMapping("/patients/{patientId}")
    public String deletePatient(@PathVariable int patientId){

        Patient patient = patientService.findById(patientId);

        // throw exception if null
        if(patient == null){
            throw new RuntimeException("Patient not found by id: " + patientId);
        }

        patientService.deleteById(patientId);

        return "Patient by id: " + patientId + " - DELETED";
    }
}
