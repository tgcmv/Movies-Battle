package com.battle.movie.controller;

import com.battle.movie.model.dto.LoginForm;
import com.battle.movie.model.dto.TokenDto;
import com.battle.movie.service.IUserService;
import com.battle.movie.service.impl.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authService;

    @PostMapping
    public ResponseEntity<TokenDto> singup(@RequestBody @Valid LoginForm form) {
        log.info("m=singup " + form.getName());
        UsernamePasswordAuthenticationToken loginData = form.convert();

            service.register(form);

            Authentication authenticate = authenticationManager.authenticate(loginData);
            String token = authService.generateToken(authenticate);

            return ResponseEntity.ok(
                    TokenDto.builder().token(token).type("Bearer").build()
            );
    }
}
