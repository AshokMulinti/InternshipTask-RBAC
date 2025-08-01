package com.ashok.example_rbac.service.interfaces;

import com.ashok.example_rbac.dto.StudentCreateRequest;
import com.ashok.example_rbac.dto.StudentCreateResponse;
import com.ashok.example_rbac.dto.StudentRequest;
import com.ashok.example_rbac.dto.StudentResponse;

public interface StudentService {
    StudentResponse getResultByMail(StudentRequest studentRequest);
    StudentCreateResponse createStudent(StudentCreateRequest request);
    StudentCreateResponse updateStudent(Long id, StudentCreateRequest request);
}
