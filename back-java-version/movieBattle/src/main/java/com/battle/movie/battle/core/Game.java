package com.battle.movie.battle.core;

import com.battle.movie.battle.model.Movie;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Game {
    @Getter
    private String userId;

    @Getter
    private GameStatus status;

    private OMDBAPIAccess omdbAPI;

    private Map<String, BigDecimal> mapMovies;

    @Getter
    private Set<Movie> movies;

    private boolean waitingAnswer;

    @Getter
    private int countFails;

    @Getter
    private int score;

    public Game(String userId) {
        this.userId = userId;
        this.status = GameStatus.READY;
        this.waitingAnswer = false;
        this.mapMovies = new HashMap<>();
        this.movies = new HashSet<>();
        this.omdbAPI = OMDBAPIAccess.getInstance();
        this.countFails = 0;

    }

    public void start() {
        if (!GameStatus.READY.equals(this.status)) {
            //TODO mesage
            throw new IllegalStateException("");
        }
        this.status = GameStatus.START;
    }

    public void end() {
        if (!GameStatus.START.equals(this.status)) {
            //TODO mesage
            throw new IllegalStateException("");
        }
        this.status = GameStatus.END;
    }

    public Set<Movie> nextMovies() {
        if (!gameHasStarted() || waitingAnswer) {
            //TODO mesage
            throw new IllegalStateException("");
        }

        waitingAnswer = true;

        Movie movie = omdbAPI.getMovie();
        Movie movie2 = omdbAPI.getMovie();

        movies.clear();
        movies.add(movie);
        movies.add(movie2);
        mapMovies.clear();
        mapMovies.put(movie.getImdbID().toUpperCase(), movie.getImdbRating());
        mapMovies.put(movie2.getImdbID().toUpperCase(), movie2.getImdbRating());

        //TODO verificar se os filmes já foram escolhidos

        return movies;
    }

    private boolean gameHasStarted() {
        return GameStatus.START.equals(this.status);
    }

    public boolean chooseMovie(String imdbID) {
        String choosedId = imdbID.toUpperCase();
        if (!mapMovies.containsKey(choosedId)) {
            //TODO mesage
            throw new IllegalStateException("");
        }

        if (!waitingAnswer) {
            //TODO mesage
            throw new IllegalStateException("");
        }

        String idMovieWithMaxScore = getIdMovieWithMaxScore();

        this.waitingAnswer = false;
        if (idMovieWithMaxScore.equalsIgnoreCase(choosedId)) {
            score++;
            return true;
        }

        countFails++;

        if (countFails >= 3) {
            //TODO mensagem
            this.end();
        }

        return false;
    }

    protected String getIdMovieWithMaxScore() {
        return mapMovies.entrySet().stream().max((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()) > 1 ? 1 : -1).get().getKey();
    }
}
