package com.example.demo.config.authen;

import com.example.demo.config.jwt.JwtTokenProvider;
import com.example.demo.users.authenDTO.LoginDTO;
import com.example.demo.users.authenDTO.LoginResponeJwt;
import com.example.demo.users.entity.Role;
import com.example.demo.users.entity.User;
import com.example.demo.users.repository.RoleRepository;
import com.example.demo.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Collections;


@RestController
@CrossOrigin
@RequestMapping(path = "api/auth")
public class Authen {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDto) throws UnsupportedEncodingException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUserName(), loginDto.getPassword()));
        System.out.println( "check authorities: " + authentication.getAuthorities());
        System.out.println( "check principal: " + authentication.getPrincipal());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new LoginResponeJwt(token));
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<?> registerUser(@RequestBody LoginDTO signUpRequest) {
        Boolean check = userRepository.existsByUserName(signUpRequest.getUserName());
        if (check) return ResponseEntity.badRequest().body("username is already taken " + signUpRequest.getUserName());

        Role role = roleRepository.findRolesByRoleTitle("user").get();
        User newUser = new User()
                .setUserName(signUpRequest.getUserName())
                .setPassword(passwordEncoder.encode(signUpRequest.getPassword()))
                .setRoleSet(Collections.singleton(role));
        userRepository.save(newUser);
        return ResponseEntity.accepted().body(newUser);
    }


}
