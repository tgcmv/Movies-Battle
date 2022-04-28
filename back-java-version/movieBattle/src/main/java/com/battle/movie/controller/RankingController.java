package com.battle.movie.controller;

import com.battle.movie.model.dto.RankingDTO;
import com.battle.movie.service.IRankingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/ranking")
@Slf4j
public class RankingController {

    private final IRankingService service;

    public RankingController(IRankingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<RankingDTO>> getRanking() {
        log.info("m=getRanking");
        Collection<RankingDTO> topRanking = service.getTopRanking();

        return ResponseEntity.ok(topRanking);
    }
}
