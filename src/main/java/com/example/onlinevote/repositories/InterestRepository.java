package com.example.onlinevote.repositories;


import com.example.onlinevote.models.Interest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InterestRepository extends CrudRepository<Interest,Integer> {
    List<Interest> findAllById(int id);
    Interest getByName(String name);
}
