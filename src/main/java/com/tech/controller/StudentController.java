package com.tech.controller;
import com.tech.model.Student;
import com.tech.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;
    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
    //hello
        return studentService.saveStudent(student);
    }
    @GetMapping("/get-all-students")
    public ResponseEntity<List<Student>> getAllBank()  {

        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/get-student/{id}")
    public ResponseEntity<Student> getBankId(@PathVariable Long id){

        return ResponseEntity.ok( studentService.getStudentById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(id, student);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<ResponseEntity<String>> deleteBank(@PathVariable Long id)
    {
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }

    @GetMapping("/students/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable String name) {
        List<Student> students = studentService.getStudentsByName(name);
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(students);
    }

}
