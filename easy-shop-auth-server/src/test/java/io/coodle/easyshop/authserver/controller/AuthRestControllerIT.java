package io.coodle.easyshop.authserver.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.core.userdetails.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthRestControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    // TODO - Fix this integration test
    @Test
    @Disabled
    void findAllUsersTest() throws Exception {
        // Given - In DataLoader

        // When
        List<User> users = testRestTemplate.getForObject("/users", List.class);

        // Then
        assertThat(users).hasSize(4); // In DataLoader, we've added 4 users
    }

}
