package com.dentechsoft.recordmanagerrestapi.service;

import com.dentechsoft.recordmanagerrestapi.entity.Patient;
import com.dentechsoft.recordmanagerrestapi.enums.ProstheticStatus;
import com.dentechsoft.recordmanagerrestapi.enums.RestorationType;

import java.util.List;

public interface PatientService {

    List<Patient> findAll();

    Patient findById(int id);

    public Patient save(Patient patient);

    void deleteById(int id);

    void setRestorationType(int patientId, RestorationType restorationType);

    void setProstheticStatus(int patientId, ProstheticStatus prostheticStatus);
}
