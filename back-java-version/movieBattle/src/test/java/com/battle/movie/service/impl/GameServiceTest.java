package com.battle.movie.service.impl;

import com.battle.movie.core.GameStatus;
import com.battle.movie.model.GameRound;
import com.battle.movie.model.Movie;
import com.battle.movie.model.PairMovies;
import com.battle.movie.model.User;
import com.battle.movie.model.dto.GameRoundDTO;
import com.battle.movie.repository.GameRoundRepository;
import com.battle.movie.repository.PairMoviesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    public static final String IMDB_ID = "movie";
    @Mock
    private GameRoundRepository repository;

    @Mock
    private MovieService movieService;

    @Mock
    private  PairMoviesRepository pairMoviesRepository;

    @InjectMocks
    private GameService service;


    @Test
    void start() {
        Mockito.when(repository.findGamesFromUser(new GameStatus[]{GameStatus.READY, GameStatus.WAITING_HIT}, getUser()))
                .thenReturn(Optional.of(GameRound.builder().build()));
        service.start(getUser());
        Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());

        Mockito.when(repository.findGamesFromUser(new GameStatus[]{GameStatus.READY, GameStatus.WAITING_HIT}, getUser()))
                .thenReturn(Optional.empty());
        service.start(getUser());
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void nextRoundWithGameNotAvaliable() {
        User user = getUser();
        assertThrows(IllegalStateException.class, () -> service.nextRound(user));
    }

    @Test
    void nextRoundWithNotAnswredMovie() {
        GameRound gameRound = GameRound.builder().build();
        gameRound.setPairMovies(Arrays.asList(PairMovies.builder().movieA("1").movieB("2").answered(false).build()));

        Mockito.when(repository.findGamesFromUser(new GameStatus[]{GameStatus.READY, GameStatus.WAITING_HIT}, getUser()))
                .thenReturn(Optional.of(gameRound));

        Mockito.when(movieService.getMovie("1"))
                .thenReturn(Movie.builder().imdbID("1").build());

        Mockito.when(movieService.getMovie("2"))
                .thenReturn(Movie.builder().imdbID("2").build());

        List<Movie> movies = service.nextRound(getUser());

        assertEquals(2, movies.size());
        assertEquals("1", movies.get(0).getImdbID());
        assertEquals("2", movies.get(1).getImdbID());
    }

    @Test
    void nextRound() {
        GameRound gameRound = GameRound.builder().build();
        gameRound.setPairMovies(Arrays.asList(PairMovies.builder().movieA("1").movieB("2").answered(true).build()));

        Mockito.when(repository.findGamesFromUser(new GameStatus[]{GameStatus.READY, GameStatus.WAITING_HIT}, getUser()))
                .thenReturn(Optional.of(gameRound));

        Mockito.when(movieService.getRandomMovie())
                .thenReturn(Movie.builder().imdbID("1").build());

        User user = getUser();
        assertThrows(UnsupportedOperationException.class, () -> service.nextRound(user));
    }

    @Test
    void hit() {
        GameRound gameRound = GameRound.builder().build();
        gameRound.setPoint(0);
        gameRound.setWrong(0);
        gameRound.setPairMovies(Arrays.asList(PairMovies.builder().movieA("1").movieB("2").answered(false).build()));

        Mockito.when(repository.findGamesFromUser(new GameStatus[]{GameStatus.READY, GameStatus.WAITING_HIT}, getUser()))
                .thenReturn(Optional.of(gameRound));

        Mockito.when(movieService.getImdbDataMovie(Mockito.anyString()))
                .thenReturn(Movie.builder().imdbRating(BigDecimal.TEN).imdbID("1").build());

        GameRoundDTO hit = service.hit(getUser(), IMDB_ID);
        assertEquals(1, hit.getPoints() + hit.getWrongs());
    }

    private User getUser() {
        return User.builder().id("1").name("user1").build();
    }

}
