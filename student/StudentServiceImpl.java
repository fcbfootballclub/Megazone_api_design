package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public int addStudent(Student student) {
        if(!studentRepository.findAll().contains(student)) {
            studentRepository.save(student);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateStudentById(Student student, long id) {
        Optional<Student> found = studentRepository.findById(id);
        if(found.isPresent()) {
            found.get().setStudentName(student.getStudentName());
            found.get().setAge(student.getAge());
            studentRepository.save(found.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteStudentById(long id) {
        Optional<Student> found = studentRepository.findById(id);
        if(found.isPresent()){
            studentRepository.delete(found.get());
            return 1;
        }
        return 0;
    }
}
