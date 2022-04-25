package com.battle.movie.config;

import com.battle.movie.model.User;
import com.battle.movie.service.impl.AuthenticationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationService service;

    public AuthenticationFilter(AuthenticationService service) {
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);
        boolean valid = service.isValidToken(token);
        if(valid){
            authenticatedClient(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticatedClient(String token) {
        User user = service.getUserFromToken(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(StringUtils.isBlank(token) || !token.substring(0, 7).trim().equalsIgnoreCase("Bearer")){
            return null;
        }

        return token.substring(7, token.length());
    }
}
