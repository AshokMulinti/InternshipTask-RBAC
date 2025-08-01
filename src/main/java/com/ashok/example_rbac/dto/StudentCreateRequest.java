package com.ashok.example_rbac.dto;

public record StudentCreateRequest(String name, String email, String course,Double percentage, String passOrFail) {
}
