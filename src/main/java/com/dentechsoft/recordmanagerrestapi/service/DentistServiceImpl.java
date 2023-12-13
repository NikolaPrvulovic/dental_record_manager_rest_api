package com.dentechsoft.recordmanagerrestapi.service;

import com.dentechsoft.recordmanagerrestapi.dao.DentistRepository;
import com.dentechsoft.recordmanagerrestapi.entity.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceImpl implements DentistService {

    private DentistRepository dentistRepository;

    @Autowired
    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public List<Dentist> findAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist findById(int id) {
        Optional<Dentist> result = dentistRepository.findById(id);

        Dentist dentist = null;

        if (result.isPresent()) {
            dentist = result.get();
        } else {
            // throw exception if there isn't any dentist by given id
            throw new RuntimeException("No dentist found by id: " + id);
        }
        return dentist;
    }

    @Override
    public Dentist save(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public void deleteById(int id) {
        dentistRepository.deleteById(id);
    }
}