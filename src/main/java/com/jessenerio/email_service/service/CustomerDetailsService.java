package com.jessenerio.email_service.service;

import com.jessenerio.email_service.document.Customer;
import com.jessenerio.email_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class CustomerDetailsService implements UserDetailsManager {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails user) {
        ((Customer) user).setPassword(passwordEncoder.encode(user.getPassword()));
        customerRepository.createCustomer((Customer) user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return customerRepository.existsByUsername(username);
    }

    public boolean isDuplicateUser(String username, String email) {
        return customerRepository.existsByUsername(username) || customerRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("username {0} not found", username)
                ));
    }
}