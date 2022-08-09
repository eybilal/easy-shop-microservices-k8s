package io.coodle.easyshop.authserver.config;

import io.coodle.easyshop.authserver.filter.JWTAuthenticationFilter;
import io.coodle.easyshop.authserver.filter.JWTAuthorizationFilter;
import io.coodle.easyshop.authserver.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
// 2nd Approach for securing resources depending on ROLES
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
@Profile("!" + SecurityProfiles.NO_AUTH)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;

    /*
        Here we specify which users have access rights.
        Specify how Spring Security will search for users and roles.
        Tell Spring Security to run this method when the user authenticate
        using the enabled formLogin of Spring Security
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

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

        /*
            HTML frames has many security breach (faille de sécurité = Agujeros de seguridad)
            Spring security blocks.
        */
        // Allow HTML frames
        http.headers().frameOptions().sameOrigin();

        // Show Login Form
        // http.formLogin();

        // Allow those requests without authentication
        http.authorizeRequests().antMatchers("/refresh-token").permitAll();

        // Require authentication for all requests
        http.authorizeRequests().anyRequest().authenticated();

        // 1rst Approach for securing resources depending on ROLES
        // http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAuthority("USER");
        // http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN");

        // Add Authentication Filter
        http.addFilter(new JWTAuthenticationFilter(authenticationManagerBean()));

        // Add Authorisation Filter
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
