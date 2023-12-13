package com.dentechsoft.recordmanagerrestapi.service;

import com.dentechsoft.recordmanagerrestapi.entity.Prosthetist;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProsthetistService extends UserDetailsService {

    List<Prosthetist> findAll();

    Prosthetist findById(int id);

    Prosthetist findByUserId(String userId);

    Prosthetist save(Prosthetist prosthetist);

    void deleteById(int id);

}
