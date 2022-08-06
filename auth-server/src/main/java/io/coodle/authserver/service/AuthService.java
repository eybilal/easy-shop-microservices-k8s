package io.coodle.authserver.service;

import io.coodle.authserver.model.entity.AppRole;
import io.coodle.authserver.model.entity.AppUser;
import io.coodle.authserver.model.pojo.UserStatus;

import java.util.List;

public interface AuthService {
    AppUser addUser(AppUser user);
    AppUser findUserByUsername(String username);
    List<AppUser> findAllUsers(UserStatus status);

    AppRole addRole(AppRole role);
    void addRoleToUser(String username, String roleName);
}
