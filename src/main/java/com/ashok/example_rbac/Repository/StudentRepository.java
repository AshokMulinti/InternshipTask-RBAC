package com.ashok.example_rbac.Repository;

import com.ashok.example_rbac.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student>findByEmail(String mail);
}
