package com.jessenerio.email_service.controller;


import com.jessenerio.email_service.model.document.User;
import com.jessenerio.email_service.model.dto.LoginDTO;
import com.jessenerio.email_service.model.dto.SignupDTO;
import com.jessenerio.email_service.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody SignupDTO signupDTO) {
        if (userService.isDuplicateUser(signupDTO.getUsername(), signupDTO.getEmail()))
            return ResponseEntity.badRequest().body("Username already exists");

        //register user
        User user = new User(signupDTO.getFirstName(), signupDTO.getLastName(), signupDTO.getUsername(), signupDTO.getEmail(), signupDTO.getPassword());
        userService.createUser(user);

        daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(user, user.getPassword()));
        return ResponseEntity.ok("User created");
    }

    @PostMapping("/logout")
    public ResponseEntity logout() {
        //TODO: logout
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        User user = (User)userService.loadUserByUsername(loginDTO.getUsername());
        daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(user, loginDTO.getPassword()));
        return ResponseEntity.ok("User logged in");
    }

}
