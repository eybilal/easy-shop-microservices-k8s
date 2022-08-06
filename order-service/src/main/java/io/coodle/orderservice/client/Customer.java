package io.coodle.orderservice.client;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String username;
    // private UserStatus status;
    private String name;
    private String email;
    private String phone;
    // private Collection<Role> roles;
}
