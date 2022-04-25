package com.battle.movie.battle.controller;

import com.battle.movie.battle.enums.UserStatus;
import com.battle.movie.battle.model.User;
import com.battle.movie.battle.model.dto.LoginForm;
import com.battle.movie.battle.model.dto.TokenDto;
import com.battle.movie.battle.service.IUserService;
import com.battle.movie.battle.service.impl.AuthenticationService;
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
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService service;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form) {
        log.info("m=authenticate "  + form.getName());
        UsernamePasswordAuthenticationToken loginData = form.convert();

        try {
            Authentication authenticate = authenticationManager.authenticate(loginData);
            String token = service.generateToken(authenticate);

            return ResponseEntity.ok(
                    TokenDto.builder().token(token).type("Bearer").build()
            );
        } catch (AuthenticationException e){
            log.error("m=authenticate "  + form.getName(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    //    @PostMapping("/register")
//    public UserStatus registerUser(@Valid @RequestBody User newUser) {
//        log.info("m=registerUser "  + newUser.toString());
//        return service.register(newUser);
//    }

//    @PostMapping("/users/logout")
//    public UserStatus logUserOut(@Valid @RequestBody User user) {
//        log.info("m=logUserOut "  + user.toString());
//        return service.logout(user);
//    }
}
