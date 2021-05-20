package com.example.onlinevote.controllers;

import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.Interest;
import com.example.onlinevote.models.User;
import com.example.onlinevote.repositories.GroupRepository;
import com.example.onlinevote.repositories.InterestRepository;
import com.example.onlinevote.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
public class AuthorizationController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final GroupRepository groupRepository;
    @Autowired
    private final InterestRepository interestRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthorizationController(UserService userService, GroupRepository groupRepository, InterestRepository interestRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.groupRepository = groupRepository;
        this.interestRepository = interestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "login-page";
    }


    @GetMapping("/profile")
    public String ProfilePage(Model model, Principal principal) {

        User me= userService.getUserByUsername(principal.getName());
        model.addAttribute("me",me);
        return "profile-page";
    }

    @GetMapping("/profile/settings")
    public String editProfilePage(Model model, Principal principal) {

        User user= userService.getUserByUsername(principal.getName());
        Iterable<Group> groupList=groupRepository.findAll();
        Iterable<Interest> interests=interestRepository.findAll();

        model.addAttribute("groups",groupList);
        model.addAttribute("interests",interests);
        model.addAttribute("me",user);

        return "edit-profile";
    }

    @PostMapping("/profile/settings")
    public String postProfilePage(Principal principal,
                                  @RequestParam(name = "txtUsername") String txtUsername,
                                  @RequestParam(name = "txtPassword") String txtPassword,
                                  @RequestParam(name = "txtInterests") List<String> interests,
                                  @RequestParam(name = "txtGroup") String group) {
        List<Interest> interestList = new ArrayList<>();
        interests.forEach(e -> interestList.add(interestRepository.getByName(e)));

        User user= userService.getUserByUsername(principal.getName());

        user.setUsername(txtUsername);
        user.setGroup(groupRepository.getGroupByName(group));
        user.setPassword(passwordEncoder.encode(txtPassword));
        user.setInterestList(interestList);

        userService.update(user);

        return "redirect:/profile";
    }

}
