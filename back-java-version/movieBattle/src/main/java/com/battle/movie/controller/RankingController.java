package com.battle.movie.controller;

<<<<<<< HEAD:back-java-version/movieBattle/src/main/java/com/battle/movie/controller/RankingController.java
import com.battle.movie.model.dto.RankingDTO;
import com.battle.movie.service.IRankingService;
=======
import com.battle.movie.battle.model.dto.RankingDTO;
import com.battle.movie.battle.service.IRankingService;
>>>>>>> b85b6d164e76689b86d8b9036c7978a6fe634f8d:back-java-version/movieBattle/src/main/java/com/battle/movie/battle/controller/RankingController.java
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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
