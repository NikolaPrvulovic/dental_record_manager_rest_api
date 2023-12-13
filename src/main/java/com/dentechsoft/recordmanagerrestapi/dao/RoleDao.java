package com.dentechsoft.recordmanagerrestapi.dao;

import com.dentechsoft.recordmanagerrestapi.entity.Role;

public interface RoleDao {

    Role findRoleByName(String roleName);
}
