package com.battle.movie.model.dto;

import com.battle.movie.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameRoundDTO {

    private Integer points;
    private Integer wrongs;
    private boolean isRightHit;
    private boolean gameOver;
    private Movie movieA;
    private Movie movieB;
}
