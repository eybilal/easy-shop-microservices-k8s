package io.coodle.easyshop.authserver.service;

import io.coodle.easyshop.authserver.model.entity.AppUser;
import io.coodle.easyshop.authserver.model.pojo.UserStatus;
import io.coodle.easyshop.authserver.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    AuthServiceImpl authService;

    // Using the @BeforeEach of MockitoExtension

    @Test
    void addUserTest() {
        // Given
        AppUser user = new AppUser();
        given(userRepository.save(any(AppUser.class))).willReturn(user);

        // When
        AppUser savedUser = authService.addUser(new AppUser());

        // Then
        assertNotNull(savedUser);
        then(userRepository).should().save(any(AppUser.class));
    }

    @Test
    void findUserByUsernameTest() {
        // Given
        AppUser user = new AppUser();
        given(userRepository.findByUsername(anyString())).willReturn(user);

        // When
        AppUser foundUser = authService.findUserByUsername("user1");

        // Then
        assertThat(foundUser).isNotNull();
        then(userRepository).should().findByUsername(anyString());  // Expect userRepository to be called once
        then(userRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    @DisplayName("findAllUsers without parameters")
    void findAllUsersTest_1() {
        // Given
        List<AppUser> users = new ArrayList<>();
        users.add(new AppUser());
        given(userRepository.findAll()).willReturn(users);

        // When
        List<AppUser> foundUsers = authService.findAllUsers(null);

        // Then
        assertThat(foundUsers.size()).isEqualTo(1);
        then(userRepository).should().findAll();
    }

    @Test
    @DisplayName("findAllUsers with status parameter")
    void findAllUsersTest_2() {
        // Given
        List<AppUser> users = new ArrayList<>();
        users.add(new AppUser());
        given(userRepository.findAllByStatus(any())).willReturn(users);

        // When
        List<AppUser> foundUsers = authService.findAllUsers(UserStatus.ACTIVE);

        // Then
        assertThat(foundUsers.size()).isEqualTo(1);
        then(userRepository).should().findAllByStatus(any());
    }

    @Disabled
    @Test
    void addRoleTest() {
    }

    @Disabled
    @Test
    void addRoleToUserTest() {
    }

    @Disabled
    @Test
    void deleteUserByIdTest() {
        // Given - none

        // When
        //authService.deleteUserById(1L);

        // Then
        then(userRepository).should().deleteById(1L);
    }

    @Disabled
    @Test
    void deleteUserByIdThrowsRuntimeExceptionTest() {
        // Given
        given(userRepository.findById(1L)).willThrow(new RuntimeException("Error message"));

        // When
        // assertThrows(RuntimeException.class, () -> deleteUserById(1L));

        // Then
        then(userRepository).should().deleteById(1L);
    }
}
