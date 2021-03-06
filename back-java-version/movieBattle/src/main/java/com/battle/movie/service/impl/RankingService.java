package com.battle.movie.service.impl;

import com.battle.movie.model.dto.RankingDTO;
import com.battle.movie.repository.GameRoundRepository;
import com.battle.movie.service.IRankingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RankingService implements IRankingService {

    private final GameRoundRepository gameRoundrepository;

    public RankingService(GameRoundRepository gameRoundrepository) {
        this.gameRoundrepository = gameRoundrepository;
    }

    @Override
    public Collection<RankingDTO> getTopRanking() {
        Map<String, RankingDTO> userScore = new HashMap<>();
        gameRoundrepository.findAll().stream().forEach(
                gr -> {
                    String userId = gr.getUser().getId();
                    if (userScore.containsKey(userId)) {
                        RankingDTO ranking = userScore.get(userId);
                        ranking.setRounds(ranking.getRounds() + 1);
                        ranking.setWrong(ranking.getWrong() + gr.getWrong());
                        ranking.setPoints(ranking.getPoints() + gr.getPoint());
                    } else {
                        RankingDTO ranking = RankingDTO.builder().name(gr.getUser().getName()).build();
                        ranking.setRounds(1);
                        ranking.setWrong(gr.getWrong());
                        ranking.setPoints(gr.getPoint());

                        userScore.put(userId, ranking);
                    }
                });

        userScore.values().stream().forEach(
                ranking -> {
                    BigDecimal points = new BigDecimal(ranking.getPoints());
                    BigDecimal tot = new BigDecimal((ranking.getWrong() + ranking.getPoints()));
                    BigDecimal hitPercentage = tot.equals(BigDecimal.ZERO) ? BigDecimal.ZERO : points.divide(tot, 2, RoundingMode.DOWN);
                    ranking.setScore(hitPercentage.multiply(new BigDecimal(ranking.getRounds())));
                }
        );


        return userScore.values();
    }
}
