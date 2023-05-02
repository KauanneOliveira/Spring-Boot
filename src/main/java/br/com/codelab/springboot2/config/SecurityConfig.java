package br.com.codelab.springboot2.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurationAdapter;

import java.net.Authenticator;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurationAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        super.configure(auth);
    }
}
