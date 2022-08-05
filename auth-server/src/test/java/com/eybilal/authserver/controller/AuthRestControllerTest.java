package com.eybilal.authserver.controller;

import com.eybilal.authserver.config.SecurityProfiles;
import com.eybilal.authserver.model.entity.AppUser;
import com.eybilal.authserver.model.pojo.UserStatus;
import com.eybilal.authserver.service.AuthService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthRestController.class)
@ActiveProfiles(SecurityProfiles.NO_AUTH)
class AuthRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    AppUser user;
    List<AppUser> users;

    @BeforeEach
    void setUp() {
        user = AppUser.builder()
                      .id(1L)
                      .username("user1")
                      .status(UserStatus.ACTIVE)
                      .createdDate(OffsetDateTime.now())
                      .lastModifiedDate(OffsetDateTime.now())
                      .build();
    }

    @AfterEach
    void tearDown() {
        reset(authService);
    }

    @Test
    void findUserByUsernameTest() throws Exception {
        // Given
        given(authService.findUserByUsername(anyString())).willReturn(user);

        // When and Then
        mockMvc.perform(get("/users" + "/username=" + user.getUsername()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.username", is(user.getUsername())));
               //.andExpect(jsonPath("$.createdDate").value(user.getCreatedDate().toString()));
    }

    @DisplayName("findAllUsersTest - No parameters")
    @Test
    void findAllUsersTest() throws Exception {
        // Given
        users = new ArrayList<>();
        users.add(user);
        given(authService.findAllUsers(any())).willReturn(users);

        // When and Then
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$.[0].id").value(user.getId()))
               .andExpect(jsonPath("$.[0].username").value(user.getUsername()));

        // Debugging version
//        MvcResult mvcResult = mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
//                                     .andExpect(status().isOk())
//                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                                     .andExpect(jsonPath("$", hasSize(1)))
//                                     .andExpect(jsonPath("$.[0].id").value(user.getId()))
//                                     .andExpect(jsonPath("$.[0].username").value(user.getUsername()))
//                                     .andReturn();
//        System.out.println("==>" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    @Disabled
    void addUser() {
    }

    @Test
    @Disabled
    void addRole() {
    }

    @Test
    @Disabled
    void addRoleToUser() {
    }
}
