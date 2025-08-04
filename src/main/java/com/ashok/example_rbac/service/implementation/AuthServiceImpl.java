package com.ashok.example_rbac.service.implementation;

import com.ashok.example_rbac.Repository.RoleRepository;
import com.ashok.example_rbac.Repository.UserRepository;
import com.ashok.example_rbac.dto.UserRequest;
import com.ashok.example_rbac.dto.SignupResponse;
import com.ashok.example_rbac.entities.Role;
import com.ashok.example_rbac.entities.User;
import com.ashok.example_rbac.exceptions.UserAlreadyExistsException;
import com.ashok.example_rbac.exceptions.UserNotFoundException;
import com.ashok.example_rbac.security.JwtUtil;
import com.ashok.example_rbac.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;
    private final RoleRepository roleRepo;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public SignupResponse registerNewUser(UserRequest request){
        Optional<User> userRepo = repo.findByUsername(request.username());

        if(userRepo.isEmpty()){
            Role userRole = roleRepo.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("USER role not found"));
            User user = User.builder()
                    .username(request.username())
                    .password(passwordEncoder.encode(request.password()))
                    .roles(Set.of(userRole))
                    .build();
            User saved = repo.save(user);
            return new SignupResponse(saved.getUsername(), request.password());
        }
        throw new UserAlreadyExistsException("User already Exists");
    }

    @Override
    public String login(UserRequest request){
        Optional<User> userRepo = repo.findByUsername(request.username());
        if(userRepo.isEmpty()){
            throw new UserNotFoundException("User Not Found");
        }
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()));
        User dbUser = repo.findByUsername(request.username()).orElseThrow();
        List<String> roles = dbUser.getRoles().stream().map(Role::getName).toList();
        String token = jwtUtil.generateToken(request.username(), roles);
        return token;
    }
}
