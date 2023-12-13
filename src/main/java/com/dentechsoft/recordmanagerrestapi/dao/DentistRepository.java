package com.dentechsoft.recordmanagerrestapi.dao;

import com.dentechsoft.recordmanagerrestapi.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistRepository extends JpaRepository<Dentist, Integer> {
}
