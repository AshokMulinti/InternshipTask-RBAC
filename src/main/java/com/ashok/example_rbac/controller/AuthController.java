package com.ashok.example_rbac.controller;

import com.ashok.example_rbac.Repository.RoleRepository;
import com.ashok.example_rbac.Repository.UserRepository;
import com.ashok.example_rbac.dto.UserRequest;
import com.ashok.example_rbac.dto.SignupResponse;
import com.ashok.example_rbac.entities.Role;
import com.ashok.example_rbac.entities.User;
import com.ashok.example_rbac.security.JwtUtil;
import com.ashok.example_rbac.service.interfaces.AuthService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<SignupResponse> register(@RequestBody UserRequest request) {
        SignupResponse response = service.registerNewUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest request) {
       String token = service.login(request);
       return ResponseEntity.ok("token: "+token);
    }
}