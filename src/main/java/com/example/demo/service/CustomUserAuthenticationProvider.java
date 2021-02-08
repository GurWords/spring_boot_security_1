package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class CustomUserAuthenticationProvider implements AuthenticationProvider {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = service.selectUserByName(name);
        if (user == null)throw new BadCredentialsException("No name");
        if (user.getPassword().equals(password)){
            UserDetails principal = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getName())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .authorities(user.getRole())
                    .build();
            return new UsernamePasswordAuthenticationToken(principal.getUsername(),principal.getPassword(),principal.getAuthorities());
        } else {
            throw new BadCredentialsException("No authentication");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
