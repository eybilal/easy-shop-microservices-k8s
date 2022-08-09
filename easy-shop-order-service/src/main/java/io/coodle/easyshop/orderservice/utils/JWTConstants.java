package io.coodle.easyshop.orderservice.utils;

public class JWTConstants {
    // TODO Put those constants in application properties and inject them with @value
    public static final String SECRET = "eybilal@gmail.com";    // TODO Change it
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 300000L; // 5 min --> (5 * 60 * 1000) ms
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 2592000000L; // 30 days --> (30 * 24 * 60 * 60 * 1000) ms
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
}
