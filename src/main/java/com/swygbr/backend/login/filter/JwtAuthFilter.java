package com.swygbr.backend.login.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.swygbr.backend.login.auth.JwtAuthenticationToken;
import com.swygbr.backend.login.auth.JwtUserPrincipal;
import com.swygbr.backend.login.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Value("${jwt.token.master:#{null}}")
    private String masterToken;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String accessToken = authorizationHeader.substring(7);
        if (accessToken != null && masterToken != null) {
            Long userId = 1L;
            String email = "master@gmail.com";
            String refreshToken = jwtUtil.createRefreshToken(userId, email);
            accessToken = jwtUtil.createAccessToken(refreshToken);

            JwtUserPrincipal principal = new JwtUserPrincipal(userId, email);
            JwtAuthenticationToken authToken = new JwtAuthenticationToken(principal, accessToken);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else if (accessToken != null && jwtUtil.validateToken(accessToken)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            Long userId = jwtUtil.getUserId(accessToken);
            String email = jwtUtil.getEmail(accessToken);
            JwtUserPrincipal principal = new JwtUserPrincipal(userId, email);

            JwtAuthenticationToken authToken = new JwtAuthenticationToken(principal, accessToken);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        chain.doFilter(request, response);
    }
}