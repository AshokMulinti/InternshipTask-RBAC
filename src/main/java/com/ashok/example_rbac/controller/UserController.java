package com.ashok.example_rbac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<String> profile() {
        return ResponseEntity.ok("User profile data");
    }
}
