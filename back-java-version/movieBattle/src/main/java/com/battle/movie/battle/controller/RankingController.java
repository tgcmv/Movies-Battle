package com.battle.movie.battle.controller;

import com.battle.movie.battle.model.dto.RankingDTO;
import com.battle.movie.battle.service.IMovieService;
import com.battle.movie.battle.service.IRankingService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/public")
@Slf4j
public class RankingController {

    private final IRankingService service;

    public RankingController(IRankingService service) {
        this.service = service;
    }

    @GetMapping("/ranking")
    public ResponseEntity<Collection<RankingDTO>> getRanking() {
        log.info("m=getRanking");
        Collection<RankingDTO> topRanking = service.getTopRanking();

        return ResponseEntity.ok(topRanking);
    }
}
