package com.tech.service.impl;
import com.tech.model.Student;
import com.tech.repository.StudentRepository;
import com.tech.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student saveStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }

        if (student.getPhone() == null || student.getPhone().length() != 10) {

            throw new IllegalArgumentException("Invalid phone number. Must be 10 digits.");
        }

        return studentRepository.save(student);
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(Math.toIntExact(id));
        if (studentOpt.isPresent()) {
            return studentOpt.get();
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }

    public Student updateStudent(Long id, Student updatedStudent) {

        Optional<Student> existingstudent = studentRepository.findById(Math.toIntExact(id));

        if (existingstudent.isPresent()) {
            Student student = existingstudent.get();
            student.setName(updatedStudent.getName());
            student.setPhone(updatedStudent.getPhone());
            student.setPercentage(updatedStudent.getPercentage());
            return studentRepository.save(student);
        } else {

            throw new RuntimeException("Student not found with id: " + id);
        }
    }


    @Override
    public ResponseEntity<String> deleteStudentById(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(Math.toIntExact(id));
        if (studentOpt.isPresent()) {
            studentRepository.deleteById(Math.toIntExact(id));
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found with id: " + id);
        }
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
    }


}