package com.example.demo.student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public List<Student> getAllStudent();
    public Optional<Student> getStudentById(long id);
    public int addStudent(Student student);
    public int updateStudentById(Student student, long id);
    public int deleteStudentById(long id);
}
