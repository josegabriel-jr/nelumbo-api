package com.nelumbo.api.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String beareToken = request.getHeader("Authorization");

        if (beareToken != null && beareToken.startsWith("Bearer ")) {
            String token = beareToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        }
        filterChain.doFilter(request, response);
    }

}