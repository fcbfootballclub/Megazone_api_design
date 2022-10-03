package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "students")
@CrossOrigin
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable long studentId) {
        Optional<Student> found = studentService.getStudentById(studentId);
        return found.isPresent()? ResponseEntity.ok(found.get()) : ResponseEntity.badRequest().body("Not found student id " + studentId);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        int i = studentService.addStudent(student);
        return i == 1? ResponseEntity.accepted().body(student) : ResponseEntity.badRequest().body("Student already exists");
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable long studentId, @RequestBody Student student) {
        int i = studentService.updateStudentById(student, studentId);
        return i == 1? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable long id) {
        int i = studentService.deleteStudentById(id);
        return i == 1? ResponseEntity.ok("delete successfully!") : ResponseEntity.badRequest().body("Not found student id " + id);
    }
}
