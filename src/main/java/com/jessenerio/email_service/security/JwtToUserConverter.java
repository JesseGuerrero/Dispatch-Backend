package com.jessenerio.email_service.security;

import com.jessenerio.email_service.document.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        Customer user = new Customer();
        user.setId(jwt.getSubject());
        return new UsernamePasswordAuthenticationToken(user, jwt, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
