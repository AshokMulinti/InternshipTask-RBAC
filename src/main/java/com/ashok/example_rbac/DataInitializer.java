package com.ashok.example_rbac;

import com.ashok.example_rbac.Repository.RoleRepository;
import com.ashok.example_rbac.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepo;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepo.findByName("USER").isEmpty()) {
            roleRepo.save(Role.builder().name("USER").build());
        }
        if (roleRepo.findByName("ADMIN").isEmpty()) {
            roleRepo.save(Role.builder().name("ADMIN").build());
        }
    }
}