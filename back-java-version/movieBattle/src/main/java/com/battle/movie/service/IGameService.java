package com.battle.movie.service;

import com.battle.movie.model.Movie;
import com.battle.movie.model.User;

import java.util.List;

public interface IGameService {
    List<Movie> nextRound(User user);

    boolean hit(User user, String imdbID);
}
