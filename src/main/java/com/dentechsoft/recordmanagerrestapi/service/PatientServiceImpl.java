package com.dentechsoft.recordmanagerrestapi.service;

import com.dentechsoft.recordmanagerrestapi.dao.PatientRepository;
import com.dentechsoft.recordmanagerrestapi.entity.Patient;
import com.dentechsoft.recordmanagerrestapi.entity.PatientDetail;
import com.dentechsoft.recordmanagerrestapi.entity.ToothStatus;
import com.dentechsoft.recordmanagerrestapi.enums.ProstheticStatus;
import com.dentechsoft.recordmanagerrestapi.enums.RestorationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(int id) {
        Optional<Patient> result = patientRepository.findById(id);

        Patient patient = null;

        if(result.isPresent()){
            patient = result.get();
        } else{
            // throw exception if there isn't any patient by given id
            throw new RuntimeException("No patient found by id: " + id);
        }
        return patient;
    }

    @Override
    @Transactional
    public Patient save(Patient patient) {



        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public void setRestorationType(int patientId, RestorationType restorationType) {

        Patient patient = findById(patientId);

        if(patient == null){
            throw new RuntimeException("Patient not found by id: " + patientId);
        }

        patient.getPatientDetail().setRestorationType(restorationType);
        patientRepository.save(patient);
    }

    @Override
    public void setProstheticStatus(int patientId, ProstheticStatus prostheticStatus) {

        Patient patient = findById(patientId);

        if(patient == null){
            throw new RuntimeException("Patient not found by id: " + patientId);
        }

        patient.getPatientDetail().setProstheticStatus(prostheticStatus);
        patientRepository.save(patient);
    }
}