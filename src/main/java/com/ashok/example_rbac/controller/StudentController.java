package com.ashok.example_rbac.controller;

import com.ashok.example_rbac.dto.StudentCreateRequest;
import com.ashok.example_rbac.dto.StudentCreateResponse;
import com.ashok.example_rbac.dto.StudentRequest;
import com.ashok.example_rbac.dto.StudentResponse;
import com.ashok.example_rbac.service.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    @PostMapping("/view")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<StudentResponse> studentResult(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentService.getResultByMail(studentRequest);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentCreateResponse> createStudent(@RequestBody StudentCreateRequest request) {
        StudentCreateResponse response = studentService.createStudent(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentCreateResponse> updateStudent(@PathVariable Long id, @RequestBody StudentCreateRequest request) {
        StudentCreateResponse studentCreateResponse = studentService.updateStudent(id, request);
        return ResponseEntity.ok(studentCreateResponse);
    }
}
