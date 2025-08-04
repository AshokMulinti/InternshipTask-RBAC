package com.ashok.example_rbac.service.interfaces;

import com.ashok.example_rbac.dto.UserRequest;
import com.ashok.example_rbac.dto.SignupResponse;

public interface AuthService {
    SignupResponse registerNewUser(UserRequest request);
    String login(UserRequest request);
}
