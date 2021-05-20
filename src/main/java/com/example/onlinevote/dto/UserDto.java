package com.example.onlinevote.dto;

import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.Interest;
import com.example.onlinevote.models.Role;
import com.example.onlinevote.models.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private int id;

    private String username;


    private Role role;
    private List<Interest> interestList=new ArrayList<>();

    public UserDto() {
    }
    public UserDto(User user){
        setId(user.getId());
        setInterestList(user.getInterestList());
        setUsername(user.getUsername());
        user.setRole(user.getRole());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Interest> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Interest> interestList) {
        this.interestList = interestList;
    }
}
