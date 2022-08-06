package io.coodle.authserver.service;

import io.coodle.authserver.model.entity.AppRole;
import io.coodle.authserver.model.entity.AppUser;
import io.coodle.authserver.model.pojo.UserStatus;
import io.coodle.authserver.repository.RoleRepository;
import io.coodle.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
/**
 * Methods are Transactional, means that JPA run commit after calling
 * the method
 */
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser addUser(AppUser user) {
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> findAllUsers(UserStatus status) {
        if (status != null) {
            return userRepository.findAllByStatus(status);
        }

        return userRepository.findAll();
    }

    @Override
    public AppRole addRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username);
        AppRole role = roleRepository.findByName(roleName);

        user.addRole(role);
    }
}
