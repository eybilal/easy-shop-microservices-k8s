package com.eybilal.authserver.bootstrap;

import com.eybilal.authserver.model.entity.AppRole;
import com.eybilal.authserver.model.entity.AppUser;
import com.eybilal.authserver.model.pojo.UserStatus;
import com.eybilal.authserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        initUsersAndRoles();
    }

    private void initUsersAndRoles() {
        AppRole userRole = authService.addRole(AppRole.builder().name("USER").build());
        AppRole adminRole = authService.addRole(AppRole.builder().name("ADMIN").build());
        AppRole customerManagerRole = authService.addRole(AppRole.builder().name("CUSTOMER_MANAGER").build());
        AppRole inventoryManagerRole = authService.addRole(AppRole.builder().name("INVENTORY_MANAGER").build());

        AppUser user1 = AppUser.builder()
                               .username("user1")
                               .password("user1.")
                               .status(UserStatus.ACTIVE)
                               .createdDate(OffsetDateTime.now())
                               .lastModifiedDate(OffsetDateTime.now())
                               .build();
        user1.addRole(userRole);
        authService.addUser(user1);

        AppUser user2 = AppUser.builder()
                               .username("user2")
                               .password("user2.")
                               .status(UserStatus.ACTIVE)
                               .createdDate(OffsetDateTime.now())
                               .lastModifiedDate(OffsetDateTime.now())
                               .build();
        user2.addRole(userRole);
        user2.addRole(customerManagerRole);
        authService.addUser(user2);

        AppUser user3 = AppUser.builder()
                               .username("user3")
                               .password("user3.")
                               .status(UserStatus.ACTIVE)
                               .createdDate(OffsetDateTime.now())
                               .lastModifiedDate(OffsetDateTime.now())
                               .build();
        user3.addRole(userRole);
        user3.addRole(inventoryManagerRole);
        authService.addUser(user3);

        AppUser admin = AppUser.builder()
                               .username("admin")
                               .password("admin.")
                               .status(UserStatus.ACTIVE)
                               .createdDate(OffsetDateTime.now())
                               .lastModifiedDate(OffsetDateTime.now())
                               .build();
        admin.addRole(userRole);
        admin.addRole(adminRole);
        authService.addUser(admin);
    }
}
