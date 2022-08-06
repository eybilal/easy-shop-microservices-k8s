package com.eybilal.orderservice.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsernamePassword {
    private String username;
    private String password;
}
