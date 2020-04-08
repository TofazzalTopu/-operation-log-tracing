package com.asl.pms.service;

import com.asl.pms.mymodel.Role;
import com.asl.pms.repository.RoleRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;

    public Role getOne(Long id) {
        return roleRepo.findById(id).get();
    }

    public List<Role> getAll() {
        return (List<Role>) roleRepo.findAll();
    }

    public void saveRole(Role role) {
        roleRepo.save(role);
    }

    public List<Role> findByNameIsNotCustomer() {
        List<Role> roleList = roleRepo.findByNameIsNot("CUSTOMER");
        return roleList;
    }
    public List<Role> findByNameIsCustomer() {
        List<Role> roleList = roleRepo.findByNameIs("CUSTOMER");
        return roleList;
    }


}
