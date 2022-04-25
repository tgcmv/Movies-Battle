package com.battle.movie.controller;

import com.battle.movie.model.Movie;
import com.battle.movie.service.IGameService;
import com.battle.movie.service.impl.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {

    private final IGameService service;

    private final AuthenticationService authService;

    public GameController(IGameService service,
                          AuthenticationService authService) {
        this.authService = authService;
        this.service = service;
    }

    @GetMapping("/next-round")
    public ResponseEntity<List<Movie>> nextRound(@RequestHeader("Authorization") String token) {
        log.info("m=nextRound");

        List<Movie> movies = service.nextRound(authService.getUserId(token));

        return ResponseEntity.ok(movies);
    }

    @PostMapping("/hit")
    public ResponseEntity<Boolean> hit(@RequestHeader("Authorization") String token, @RequestBody String imdbID) {
        log.info("m=hit");

        boolean isRightHit = service.hit(authService.getUserId(token), imdbID);

        return ResponseEntity.ok(isRightHit);
    }
}
