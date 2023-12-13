package com.dentechsoft.recordmanagerrestapi.dao;

import com.dentechsoft.recordmanagerrestapi.entity.Prosthetist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProsthetistUserDaoImpl implements ProsthetistUserDao{

    private EntityManager entityManager;

    @Autowired
    public ProsthetistUserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Prosthetist findByUserId(String userId) {

        // retrieve/read user id from database
        TypedQuery<Prosthetist> query = entityManager.createQuery(
                "SELECT p FROM Prosthetist p LEFT JOIN FETCH p.roles WHERE p.userId = :uId", Prosthetist.class);

        query.setParameter("uId", userId);

        Prosthetist prosthetist = null;
        try{
            prosthetist = query.getSingleResult();
        } catch (Exception e){
            prosthetist = null;
        }

        return prosthetist;

    }
}
