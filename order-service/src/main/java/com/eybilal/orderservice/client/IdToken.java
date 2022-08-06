package com.eybilal.orderservice.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdToken {
    private String accessToken;
    private String refreshToken;
}
