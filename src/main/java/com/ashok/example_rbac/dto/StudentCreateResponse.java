package com.ashok.example_rbac.dto;

public record StudentCreateResponse(Long id, String name, String email, String course, Double percentage, String passOrFail) {
}
