package com.dentechsoft.recordmanagerrestapi.service;

import com.dentechsoft.recordmanagerrestapi.dao.ProsthetistRepository;
import com.dentechsoft.recordmanagerrestapi.dao.ProsthetistUserDao;
import com.dentechsoft.recordmanagerrestapi.dao.RoleDao;
import com.dentechsoft.recordmanagerrestapi.entity.Prosthetist;
import com.dentechsoft.recordmanagerrestapi.entity.Role;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProsthetistServiceImpl implements ProsthetistService{

    private ProsthetistRepository prosthetistRepository;
    private ProsthetistUserDao prosthetistUserDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ProsthetistServiceImpl(ProsthetistRepository prosthetistRepository,
                                  ProsthetistUserDao prosthetistUserDao, RoleDao roleDao,
                                  BCryptPasswordEncoder passwordEncoder) {
        this.prosthetistRepository = prosthetistRepository;
        this.prosthetistUserDao = prosthetistUserDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Prosthetist> findAll() {
        return prosthetistRepository.findAll();
    }

    @Override
    public Prosthetist findById(int id) {
        Optional<Prosthetist> result = prosthetistRepository.findById(id);

        Prosthetist prosthetist = null;

        if(result.isPresent()){
            prosthetist = result.get();
        } else{
            // throw exception if there isn't any prosthetist by given id
            throw new RuntimeException("No prosthetist found by id: " + id);
        }
        return prosthetist;
    }

    @Override
    public Prosthetist findByUserId(String userId) {
        return prosthetistUserDao.findByUserId(userId);
    }

    @Override
    @Transactional
    public Prosthetist save(Prosthetist prosthetist) {

        // save BCrypted password
        prosthetist.setPassword(passwordEncoder.encode(prosthetist.getPassword()));

        // set entry Role as Prosthetist
        prosthetist.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_PROSTHETIST")));

        return prosthetistRepository.save(prosthetist);
    }

    @Override
    public void deleteById(int id) {
        Prosthetist tempProsthetist = findById(id);

        // unassociate Prosthetist with Role so it can be deleted
        tempProsthetist.setRoles(null);

        prosthetistRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Prosthetist prosthetist = prosthetistUserDao.findByUserId(username);

        if(prosthetist == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }

        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(prosthetist.getRoles());
        System.out.println("Username: " + prosthetist.getUserId());
        System.out.println("Password: " + prosthetist.getPassword());
        System.out.println("Authorities: " + authorities);


        return new User(prosthetist.getUserId(), prosthetist.getPassword(), authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        System.out.println("Roles before mapping: " + roles);

        for (Role role:
             roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            authorities.add(authority);
            System.out.println("Role" + role.getName());
        }
        System.out.println("Authorities: " + authorities);
        return authorities;
    }
}
