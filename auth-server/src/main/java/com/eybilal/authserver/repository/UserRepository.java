package com.eybilal.authserver.repository;

import com.eybilal.authserver.model.entity.AppUser;
import com.eybilal.authserver.model.pojo.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    List<AppUser> findAllByStatus(UserStatus status);
}
