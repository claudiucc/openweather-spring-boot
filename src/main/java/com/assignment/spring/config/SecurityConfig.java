package com.assignment.spring.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private ServiceConfig serviceConfig;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(serviceConfig.getAuthentication().getUser().getUsername()).password("{noop}" + serviceConfig.getAuthentication().getUser().getPassword()).roles("USER")
                .and()
                .withUser(serviceConfig.getAuthentication().getAdmin().getUsername()).password("{noop}" + serviceConfig.getAuthentication().getAdmin().getPassword()).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/openweather-api/v1/weather/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/openweather-api/v1/weatherInfo/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}