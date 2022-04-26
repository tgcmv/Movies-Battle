package com.battle.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameRoundDTO {

    private Integer points;
    private Integer wrongs;
    private boolean isRightHit;
    private boolean gameOver;
    private String imdbA;
    private String imdbB;
    private BigDecimal rankingA;
    private BigDecimal rankingB;
}
