package com.jessenerio.email_service.controller;


import com.jessenerio.email_service.model.document.User;
import com.jessenerio.email_service.model.dto.LoginDTO;
import com.jessenerio.email_service.model.dto.SignupDTO;
import com.jessenerio.email_service.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    DaoAuthenticationProvider authenticationManager;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody SignupDTO signupDTO) {
        if (userService.isDuplicateUser(signupDTO.getUsername(), signupDTO.getEmail()))
            return ResponseEntity.badRequest().body("Username already exists");

        //register user
        User user = new User(signupDTO.getFirstName(), signupDTO.getLastName(), signupDTO.getUsername(), signupDTO.getEmail(), signupDTO.getPassword());
        userService.createUser(user);

        return ResponseEntity.ok("User created");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();

        return ResponseEntity.ok("User logged out");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        User user = (User) userService.loadUserByUsername(loginDTO.getUsername());
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user, loginDTO.getPassword())
        );

        // Set the authentication in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("User logged in");
    }

}
