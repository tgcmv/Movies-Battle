package com.battle.movie.battle.core;

import com.battle.movie.battle.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTests {

    private Game game;

    private static final String USER_ID = "UserTest";

    @BeforeEach
    void init() {
        this.game = new Game(USER_ID);
        this.game.start();
    }

    @Test
    @DisplayName("Iniciar uma partida")
    void startGame() {
        Game startGame = new Game(USER_ID);
        startGame.start();
        assertEquals(GameStatus.START, startGame.getStatus());
        assertThrows(IllegalStateException.class, () -> game.start());
    }

    @Test
    @DisplayName("Encerrar uma partida")
    void endGame() {
        game.end();
        assertEquals(GameStatus.END, game.getStatus());

        assertThrows(IllegalStateException.class, () -> game.end());
    }

    @Test
    @DisplayName("Pegar uma lista de filmes para dar o palpite")
    void getMovies() {
        Set<Movie> movies = game.nextMovies();
        assertEquals(2, movies.size());
        assertThrows(IllegalStateException.class, () -> game.nextMovies());
    }

    @Test
    @DisplayName("Palpite inválido")
    void invalidGuess() {
        game.nextMovies();
        assertThrows(IllegalStateException.class, () -> game.chooseMovie("test"));
        String imdbID = game.getMovies().stream().findFirst().get().getImdbID();
        game.chooseMovie(imdbID);
        assertThrows(IllegalStateException.class, () -> game.chooseMovie(imdbID));
    }

    @Test
    @DisplayName("Acertar o palpite de filme com maior pontuação")
    void rightGuess() {
        game.nextMovies();
        assertTrue(game.chooseMovie(game.getIdMovieWithMaxScore()));
    }

    @Test
    @DisplayName("Errar o palpite de filme com maior pontuação")
    void wrongGuess() {
        game.nextMovies();
        final String idMovieWithMaxScore = game.getIdMovieWithMaxScore();
        assertFalse(game.chooseMovie(game.getMovies().stream().filter(m -> !m.getImdbID().equalsIgnoreCase(idMovieWithMaxScore)).findFirst().get().getImdbID()));
    }

    @Test
    @DisplayName("Perder o jogo com 3 palpites errados")
    void loseGame() {
        game.nextMovies();
        final String idMovieWithMaxScore = game.getIdMovieWithMaxScore();
        assertFalse(game.chooseMovie(game.getMovies().stream().filter(m -> !m.getImdbID().equalsIgnoreCase(game.getIdMovieWithMaxScore())).findFirst().get().getImdbID()));

        game.nextMovies();
        assertFalse(game.chooseMovie(game.getMovies().stream().filter(m -> !m.getImdbID().equalsIgnoreCase(game.getIdMovieWithMaxScore())).findFirst().get().getImdbID()));

        game.nextMovies();
        assertFalse(game.chooseMovie(game.getMovies().stream().filter(m -> !m.getImdbID().equalsIgnoreCase(game.getIdMovieWithMaxScore())).findFirst().get().getImdbID()));

        assertEquals(GameStatus.END, game.getStatus());
    }
}
