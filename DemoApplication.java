package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.example.demo.users.entity.Role;
import com.example.demo.users.entity.User;
import com.example.demo.users.repository.RoleRepository;
import com.example.demo.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication  implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //run first time only
        Role role = new Role().setRoleTitle("user");
        Optional<Role> user1 = roleRepository.findRolesByRoleTitle("user");
        User user = new User()
                .setUserName("henry")
                .setPassword(passwordEncoder.encode("123456"));

        Student student = new Student().setStudentName("Henry").setAge(18);
        userRepository.save(user);
        studentRepository.save(student);
    }
}
