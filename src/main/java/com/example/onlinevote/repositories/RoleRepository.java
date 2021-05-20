package com.example.onlinevote.repositories;

import com.example.onlinevote.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role getRoleByName(String name);
}
