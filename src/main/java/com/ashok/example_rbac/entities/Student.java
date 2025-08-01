package com.ashok.example_rbac.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String course;

    private Double percentage;

    private String passOrFail;
}
