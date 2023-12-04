package com.jessenerio.email_service.config.security;

import com.jessenerio.email_service.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurity {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @Bean
    public SecurityFilterChain publicSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/", "/error", "/login", "/signup",
                                "/images/*", "/css/*", "/js/*", "/auth/*").permitAll()
                        .antMatchers("/*").authenticated()
                )
                .csrf().disable()
                .cors().disable()
                .httpBasic().disable()
                .sessionManagement(
                        (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin()
                .loginPage("/signup");

        return http.build();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
