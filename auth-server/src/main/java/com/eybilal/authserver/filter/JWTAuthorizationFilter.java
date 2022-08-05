package com.eybilal.authserver.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eybilal.authserver.utils.JWTConstants;
import com.google.common.base.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if ("/refresh-token".equals(httpServletRequest.getServletPath())) {
            // Pass
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            String authorisationHeader = httpServletRequest.getHeader(JWTConstants.AUTHORIZATION_HEADER);

            if (!Strings.isNullOrEmpty(authorisationHeader) && authorisationHeader.startsWith(JWTConstants.TOKEN_PREFIX)) {
                try {
                    String accessToken = authorisationHeader.substring(JWTConstants.TOKEN_PREFIX.length());
                    Algorithm algorithm = Algorithm.HMAC256(JWTConstants.SECRET);
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();

                    DecodedJWT decodedJWT = jwtVerifier.verify(accessToken);
                    String username = decodedJWT.getSubject();

                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    for (String role: roles) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(role));
                    }

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            grantedAuthorities
                    );

                    // Authenticate the user
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    // Allow the user to pass to the next filter
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                } catch (Exception exception) {
                    // If jwtVerifier.verify throws an Exception (expired or invalid token)
                    httpServletResponse.setHeader("error-message", exception.getMessage());
                    httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                // Delegate to Spring Security to decide
                // If the request need an authentication, it will be rejected
                // Otherwise it will be allowed to pass
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }
    }
}
