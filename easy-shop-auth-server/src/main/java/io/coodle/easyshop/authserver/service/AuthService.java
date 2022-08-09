package io.coodle.easyshop.authserver.service;

import io.coodle.easyshop.authserver.model.entity.AppRole;
import io.coodle.easyshop.authserver.model.entity.AppUser;
import io.coodle.easyshop.authserver.model.pojo.UserStatus;

import java.util.List;

public interface AuthService {
    AppUser addUser(AppUser user);
    AppUser findUserByUsername(String username);
    List<AppUser> findAllUsers(UserStatus status);

    AppRole addRole(AppRole role);
    void addRoleToUser(String username, String roleName);
}
