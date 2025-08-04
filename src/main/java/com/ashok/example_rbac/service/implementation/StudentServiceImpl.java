package com.ashok.example_rbac.service.implementation;

import com.ashok.example_rbac.Repository.StudentRepository;
import com.ashok.example_rbac.dto.StudentCreateRequest;
import com.ashok.example_rbac.dto.StudentCreateResponse;
import com.ashok.example_rbac.dto.StudentRequest;
import com.ashok.example_rbac.dto.StudentResponse;
import com.ashok.example_rbac.entities.Student;
import com.ashok.example_rbac.exceptions.UserAlreadyExistsException;
import com.ashok.example_rbac.exceptions.UserNotFoundException;
import com.ashok.example_rbac.service.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponse getResultByMail(StudentRequest request){
        Optional<Student> studentData = studentRepository.findByEmail(request.email());

        if(studentData.isEmpty()){
            throw new UserNotFoundException("Student not found with this mail");
        }
        Student student = studentData.get();
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getCourse(),
                student.getPercentage(),
                student.getPassOrFail()
        );
    }

    @Override
    public StudentCreateResponse createStudent(StudentCreateRequest request) {
        Optional<Student> existingStudent = studentRepository.findByEmail(request.email());

        if (existingStudent.isPresent()) {
            throw new UserAlreadyExistsException("Student already exists with this email");
        }

        Student student = new Student();
        student.setName(request.name());
        student.setEmail(request.email());
        student.setCourse(request.course());
        student.setPercentage(request.percentage());
        student.setPassOrFail(request.passOrFail());

        Student savedStudent = studentRepository.save(student);

        return new StudentCreateResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getEmail(),
                savedStudent.getCourse(),
                savedStudent.getPercentage(),
                savedStudent.getPassOrFail()
        );
    }

    @Override
    public StudentCreateResponse updateStudent(Long id, StudentCreateRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Student not found by id"));
        student.setName(request.name());
        student.setEmail(request.email());
        student.setCourse(request.course());
        student.setPercentage(request.percentage());
        student.setPassOrFail(request.percentage() >= 40 ? "PASS" : "FAIL");
        Student ss = studentRepository.save(student);
      return new StudentCreateResponse(ss.getId(),ss.getName(),ss.getEmail(),ss.getCourse(),ss.getPercentage(),ss.getPassOrFail());
    }
}
