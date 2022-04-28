package com.battle.movie.service.impl;

import com.battle.movie.model.GameRound;
import com.battle.movie.model.User;
import com.battle.movie.model.dto.RankingDTO;
import com.battle.movie.repository.GameRoundRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RankingServiceTest {

    private static final String USER_1 = "user1";
    @Mock
    private GameRoundRepository gameRoundrepository;

    @InjectMocks
    private RankingService service;

    @Test
    void getTopRanking() {
        List<GameRound> listGameRound = Arrays.asList(
                getGameRound(User.builder().id("1").name(USER_1).build(), 10, 0),
                getGameRound(User.builder().id("1").name(USER_1).build(), 0, 3),
                getGameRound(User.builder().id("2").name("user2").build(), 10, 0)
                );

        Mockito.when(gameRoundrepository.findAll()).thenReturn(listGameRound);
        Collection<RankingDTO> topRanking = service.getTopRanking();
        assertEquals(2, topRanking.size());

        RankingDTO rankingDTO = topRanking.stream().findFirst().get();
        assertEquals(USER_1, rankingDTO.getName());
        assertEquals(3, rankingDTO.getWrong());
        assertEquals(10, rankingDTO.getPoints());
        assertEquals(2, rankingDTO.getRounds());


        assertEquals(new BigDecimal("1.52"), rankingDTO.getScore());

    }

    private GameRound getGameRound(User user, int points, int wrong) {
        return GameRound.builder().user(user).point(points).wrong(wrong).build();
    }

}
