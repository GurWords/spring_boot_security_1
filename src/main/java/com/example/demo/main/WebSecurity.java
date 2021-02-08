package com.example.demo.main;

import com.example.demo.service.CustomUserAuthenticationProvider;
import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


    private CustomUserDetailsService service;

    private CustomUserAuthenticationProvider provider;

    @Autowired
    WebSecurity(CustomUserDetailsService service){
        this.service = service;
    }

    @Autowired
    public void setProvider(CustomUserAuthenticationProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/insertUser").anonymous()
                .antMatchers("/printUser/**").hasAuthority("ADMIN")
                .and().formLogin();
    }
}
