package com.example.onlinevote.services;


import com.example.onlinevote.models.User;

public interface UserService extends EntityService<User>{
    User getUserByUsername(String username);
    void changePassword(String name, String password);
    User getUserById(int id);
    Iterable<User> getAllUsers();
}
