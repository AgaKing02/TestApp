package com.example.onlinevote.repositories;

import com.example.onlinevote.models.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group,Integer> {
    Group getGroupByName(String name);
}
