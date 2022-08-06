package io.coodle.orderservice.config;

import io.coodle.orderservice.filter.JWTAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
       Here we specify access rights
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // DO NOT generate synchronized token and DO NOT put it in session
        // Because, we will use stateless authentication
        http.csrf().disable();

        // Enable Stateless Authentication
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Require authentication for all requests
        http.authorizeRequests().anyRequest().authenticated();

        // Add Authorisation Filter
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

