package com.tech.service;

import com.tech.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student updatedStudent);

    ResponseEntity<String> deleteStudentById(Long id);

    List<Student> getStudentsByName(String name);
}
