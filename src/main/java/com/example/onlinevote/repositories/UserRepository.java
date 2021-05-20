package com.example.onlinevote.repositories;

import com.example.onlinevote.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserByUsername(String username);

    User getUserById(int id);
}
