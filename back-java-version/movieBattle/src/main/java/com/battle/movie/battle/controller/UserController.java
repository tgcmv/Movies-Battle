package com.battle.movie.battle.controller;

import com.battle.movie.battle.enums.UserStatus;
import com.battle.movie.battle.model.User;
import com.battle.movie.battle.model.dto.RankingDTO;
import com.battle.movie.battle.service.IRankingService;
import com.battle.movie.battle.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public UserStatus registerUser(@Valid @RequestBody User newUser) {
        log.info("m=registerUser "  + newUser.toString());
        return service.register(newUser);
    }

    @PostMapping("/login")
    public UserStatus loginUser(@Valid @RequestBody User user) {
        log.info("m=loginUser "  + user.toString());
        return service.login(user);
    }

    @PostMapping("/users/logout")
    public UserStatus logUserOut(@Valid @RequestBody User user) {
        log.info("m=logUserOut "  + user.toString());
        return service.logout(user);
    }
}
