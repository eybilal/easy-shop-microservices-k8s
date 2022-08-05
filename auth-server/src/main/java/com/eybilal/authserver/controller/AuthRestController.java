package com.eybilal.authserver.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eybilal.authserver.model.dto.UserRole;
import com.eybilal.authserver.model.entity.AppRole;
import com.eybilal.authserver.model.entity.AppUser;
import com.eybilal.authserver.model.pojo.UserStatus;
import com.eybilal.authserver.service.AuthService;
import com.eybilal.authserver.utils.JWTConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AuthRestController {
    private final AuthService authService;

    public AuthRestController(AuthService accountService) {
        this.authService = accountService;
    }

    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<AppUser>> findAllUsers(@RequestParam(value = "status", required = false) UserStatus status) {
        return new ResponseEntity<>(authService.findAllUsers(status), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{username}")
    @PostAuthorize("hasAuthority('USER')")
    public ResponseEntity<AppUser> findUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(authService.findUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping(path="/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser addUser(@RequestBody AppUser user) {
        return authService.addUser(user);
    }

    @PostMapping(path="/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppRole addRole(@RequestBody AppRole role) {
        return authService.addRole(role);
    }

    @PostMapping(path="/add-role-to-user")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody UserRole userRole) {
        authService.addRoleToUser(userRole.getUsername(), userRole.getRoleName());
    }

    @GetMapping(path = "/refresh-token")
    public void refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String authorisation = httpServletRequest.getHeader(JWTConstants.AUTHORIZATION_HEADER);

        if (authorisation != null && authorisation.startsWith(JWTConstants.TOKEN_PREFIX)) {
            try {
                String refreshToken = authorisation.substring(JWTConstants.TOKEN_PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JWTConstants.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();

                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();

                AppUser appUser = authService.findUserByUsername(username);

                // Generate new access token
                String accessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWTConstants.ACCESS_TOKEN_EXPIRATION_TIME))
                        .withIssuer(httpServletRequest.getRequestURL().toString())
                        .withClaim("roles",
                                appUser.getRoles().stream()
                                        .map(role -> role.getName())
                                        .collect(Collectors.toList())
                        ).sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", accessToken);
                idToken.put("refresh-token", refreshToken);

                httpServletResponse.setContentType("application/json");

                // Use Jackson to serialize to JSON
                new ObjectMapper().writeValue(httpServletResponse.getOutputStream(), idToken);

               // Verify the blacklist

            } catch (Exception exception) {
                throw exception;
            }
        } else {
            throw new RuntimeException("Refresh Token required");
        }
    }

    @GetMapping(path = "/profile")
    public AppUser profile(Principal principal) {
        return authService.findUserByUsername(principal.getName());
    }
}
