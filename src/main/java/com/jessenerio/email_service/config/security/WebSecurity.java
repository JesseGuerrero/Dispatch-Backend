package com.jessenerio.email_service.config.security;

import com.jessenerio.email_service.model.service.NewsletterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurity {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    NewsletterService newsletterService;

    @Bean
    public SecurityFilterChain publicSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/", "/error", "/login", "/signup",
                                "/images/*", "/css/*", "/js/*", "/auth/*").permitAll()
                        .antMatchers("/*").authenticated()
                )
                .csrf().disable()//only while debugging with Postman
                .cors().disable()
                .formLogin()
                .loginPage("/signup");

        return http.build();// Builds security chain into rules for a set of urls
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(newsletterService);
        //All incoming passwords to the authenticationManager will be encoded to check with the encoded version
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }
}
