package com.dentechsoft.recordmanagerrestapi.dao;

import com.dentechsoft.recordmanagerrestapi.entity.Prosthetist;

public interface ProsthetistUserDao {
    Prosthetist findByUserId(String userId);
}
