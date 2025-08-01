package com.ashok.example_rbac.controller;

import com.ashok.example_rbac.Repository.RoleRepository;
import com.ashok.example_rbac.Repository.UserRepository;
import com.ashok.example_rbac.entities.Role;
import com.ashok.example_rbac.entities.User;
import com.ashok.example_rbac.security.JwtUtil;
import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepo.findByName("USER").orElseThrow();
        user.setRoles(Set.of(userRole));
        userRepo.save(user);
        return "User registered.";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        User dbUser = userRepo.findByUsername(user.getUsername()).orElseThrow();
        List<String> roles = dbUser.getRoles().stream().map(Role::getName).toList();
        return jwtUtil.generateToken(user.getUsername(), roles);
    }
}