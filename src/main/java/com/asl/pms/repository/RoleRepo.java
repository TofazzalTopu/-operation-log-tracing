package com.asl.pms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asl.pms.mymodel.Role;

import java.util.List;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {

    public List<Role> findByNameIsNot(String name);
    public List<Role> findByNameIs(String name);
}
