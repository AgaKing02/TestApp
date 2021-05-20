package com.example.onlinevote.controllers;

import com.example.onlinevote.models.Role;
import com.example.onlinevote.models.User;
import com.example.onlinevote.repositories.RoleRepository;
import com.example.onlinevote.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        Iterable<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users-page";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable(name = "id") int id) {
        User user= userService.getUserById(id);
        Iterable<Role> roles= roleRepository.findAll();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
        return "user-exact-page";
    }

    @PostMapping("/{id}")
    public String setAuthority(Model model, @PathVariable(name = "id") int id, @RequestParam(name = "txtRole") String role) {
        User user = userService.getUserById(id);
        user.setRole(roleRepository.getRoleByName(role));
        userService.save(user);
        return "redirect:/users/all";
    }
}
