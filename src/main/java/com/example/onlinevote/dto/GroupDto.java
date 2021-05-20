package com.example.onlinevote.dto;

import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupDto {
    private int id;
    private String name;
    private List<UserDto> userList=new ArrayList<>();

    public GroupDto(){}
    public GroupDto(Group group){
       setId(group.getId());
       setName(group.getName());
       setUsers(group.getUserList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDto> userList) {
        this.userList = userList;
    }
    public void setUsers(List<User> users){
        users.forEach(e->this.userList.add(new UserDto(e)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupDto)) return false;
        GroupDto groupDto = (GroupDto) o;
        return getId() == groupDto.getId() && Objects.equals(getName(), groupDto.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
