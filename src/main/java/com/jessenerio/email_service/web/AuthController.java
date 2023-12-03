package com.jessenerio.email_service.web;


import com.jessenerio.email_service.document.User;
import com.jessenerio.email_service.dto.LoginDTO;
import com.jessenerio.email_service.dto.SignupDTO;
import com.jessenerio.email_service.dto.TokenDTO;
import com.jessenerio.email_service.security.TokenGenerator;
import com.jessenerio.email_service.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserDetailsService userDetailsManager;
    @Autowired
    TokenGenerator tokenGenerator;
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;
    @Autowired
    @Qualifier("jwtRefreshTokenAuthProvider")
    JwtAuthenticationProvider refreshTokenAuthProvider;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody SignupDTO signupDTO) {
        if (userDetailsManager.isDuplicateUser(signupDTO.getUsername(), signupDTO.getEmail()))
            return ResponseEntity.badRequest().body("Username already exists");

        //register user
        User user = new User(signupDTO.getFirstName(), signupDTO.getLastName(), signupDTO.getUsername(),
                signupDTO.getEmail(), signupDTO.getPassword());
        userDetailsManager.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(signupDTO.getUsername(), signupDTO.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/logout")
    public ResponseEntity logout() {
        //TODO: logout
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(), loginDTO.getPassword()));

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/token")
    public ResponseEntity token(@RequestBody TokenDTO tokenDTO) {
        Authentication authentication = refreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));
        Jwt jwt = (Jwt) authentication.getCredentials();
        // check if present in db and not revoked, etc

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }
}
