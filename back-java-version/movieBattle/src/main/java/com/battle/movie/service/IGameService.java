package com.battle.movie.service;

import com.battle.movie.model.Movie;
import com.battle.movie.model.User;
import com.battle.movie.model.dto.GameRoundDTO;

import java.util.List;

public interface IGameService {
    List<Movie> nextRound(User user);

    GameRoundDTO hit(User user, String imdbID);

    void start(User userId);
}
