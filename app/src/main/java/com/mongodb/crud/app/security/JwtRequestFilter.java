package com.mongodb.crud.app.security;

import java.io.IOException;

import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    List<String> excludeUrlPatterns = List.of("/user/login", "/user/register");

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // @Override
    // protected void doFilterInternal(HttpServletRequest request,
    // HttpServletResponse response, FilterChain chain)
    // throws ServletException, IOException {

    // final String requestTokenHeader = request.getHeader("Authorization");

    // String jwtToken = null;

    // if (!excludeUrlPatterns.contains(request.getRequestURI())) {
    // // JWT Token is in the form "Bearer token".
    // // Remove Bearer word and get only the token
    // if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
    // jwtToken = requestTokenHeader.substring(7);
    // try {
    // // if token is valid configure Spring Security to manually set authentication
    // if (!jwtTokenUtil.validate(jwtToken)) {
    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
    // return;
    // }
    // } catch (IllegalArgumentException e) {
    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
    // return;
    // } catch (ExpiredJwtException e) {
    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
    // return;
    // }
    // } else {
    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
    // return;
    // }
    // }
    // chain.doFilter(request, response);
    // }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (!excludeUrlPatterns.contains(request.getRequestURI())) {
            if (!jwtTokenUtil.isTokenValid(request)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
