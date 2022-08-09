package io.coodle.easyshop.authserver.service;

import io.coodle.easyshop.authserver.model.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser appUser = authService.findUserByUsername(s);

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        appUser.getRoles().forEach(appRole -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(appRole.getName()));
        });

        // Returns User to Spring Security.
        // Spring Security will verify if the password is correct
        // AND will verify if the users has the specific role, etc
        return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
    }
}
