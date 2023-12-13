package com.dentechsoft.recordmanagerrestapi.service;

import com.dentechsoft.recordmanagerrestapi.entity.Dentist;

import java.util.List;

public interface DentistService {

    List<Dentist> findAll();

    Dentist findById(int id);

    Dentist save(Dentist dentist);

    void deleteById(int id);
}
