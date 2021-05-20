package com.example.onlinevote.controllers;



import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.Interest;
import com.example.onlinevote.models.User;
import com.example.onlinevote.repositories.GroupRepository;
import com.example.onlinevote.repositories.InterestRepository;
import com.example.onlinevote.repositories.RoleRepository;
import com.example.onlinevote.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthenticationController {
    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final GroupRepository groupRepository;
    @Autowired
    private final InterestRepository interestRepository;
    @Autowired
    private final UserService userService;


    public AuthenticationController(RoleRepository roleRepository, GroupRepository groupRepository, InterestRepository interestRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
        this.interestRepository = interestRepository;
        this.userService = userService;
    }


    @GetMapping("/sign/up")
    public String signUpPage(Model model) {
        Iterable<Group> groupList=groupRepository.findAll();
        Iterable<Interest> interests=interestRepository.findAll();
        model.addAttribute("groups",groupList);
        model.addAttribute("interests",interests);
        return "signup-page";
    }

    @PostMapping("/sign/up")
    public String signUp(@RequestParam(name = "txtUsername") String txtUsername,
                         @RequestParam(name = "txtPassword") String txtPassword,
                         @RequestParam(name = "txtInterests") List<String> interests,
                         @RequestParam(name = "txtGroup") String group) {
        List<Interest> interestList = new ArrayList<>();
        interests.forEach(e -> interestList.add(interestRepository.getByName(e)));

        User user = new User(txtUsername,
                passwordEncoder.encode(txtPassword),
                roleRepository.getRoleByName("USER"),
                groupRepository.getGroupByName(group),
                interestList);

        userService.save(user);
        return "redirect:/login";
    }
}
