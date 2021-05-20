package com.example.onlinevote.controllers;

import com.example.onlinevote.models.Group;
import com.example.onlinevote.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    @GetMapping("/add")
    public String addGroupPage(Model model){
        model.addAttribute("group",new Group());
        return "group-add-page";
    }
    @PostMapping("/add")
    public String addGroup(@ModelAttribute Group group){
        groupRepository.save(group);
        return "redirect:/";
    }
    @GetMapping("/edit/{group}")
    public String editGroupPage(Model model, @PathVariable(name = "group")Group group){
        model.addAttribute("group",group);
        return "group-edit-page";
    }
    @PostMapping("/edit/{id}")
    public String editGroup(@PathVariable(name = "id")int id,@RequestParam(name = "txtName")String name){
        Optional<Group> group=groupRepository.findById(id);
        if(group.isPresent()){
            Group group1=group.get();
            group1.setName(name);
            groupRepository.save(group1);
        }
        return "redirect:/";
    }

    @GetMapping("/list")
    public String getAllGroups(Model model){
        model.addAttribute("groups",groupRepository.findAll());
        return "groups-page";
    }
}
