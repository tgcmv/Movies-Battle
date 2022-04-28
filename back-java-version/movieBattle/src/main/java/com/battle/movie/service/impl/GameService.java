package com.battle.movie.service.impl;

import com.battle.movie.core.GameStatus;
import com.battle.movie.model.GameRound;
import com.battle.movie.model.Movie;
import com.battle.movie.model.PairMovies;
import com.battle.movie.model.User;
import com.battle.movie.model.dto.GameRoundDTO;
import com.battle.movie.repository.GameRoundRepository;
import com.battle.movie.repository.PairMoviesRepository;
import com.battle.movie.service.IGameService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

@Service
@Slf4j
public class GameService implements IGameService {

    private final GameRoundRepository repository;

    private final PairMoviesRepository pairMoviesRepository;

    private final MovieService movieService;


    public GameService(
            GameRoundRepository repository,
            MovieService movieService,
            PairMoviesRepository pairMoviesRepository
    ) {
        this.repository = repository;
        this.movieService = movieService;
        this.pairMoviesRepository = pairMoviesRepository;
    }

    @Override
    public void start(User user) {
        Optional<GameRound> inProgressGames = repository.findGamesFromUser(new GameStatus[]{GameStatus.READY, GameStatus.WAITING_HIT}, user);

        if (!inProgressGames.isPresent()) {
            GameRound game = GameRound.builder()
                    .status(GameStatus.READY)
                    .user(user)
                    .point(0)
                    .wrong(0)
                    .build();

            repository.save(game);
        }
    }

    @Override
    public List<Movie> nextRound(User user) {
        final GameRound game = getAvaliableGameFor(user);
        final Set<String> setPairMovies = previusSoterdPairMovies(game);

        if (hasNotAnswredMovie(game)) {
            PairMovies movies = game.getPairMovies().stream().filter(Predicate.not(PairMovies::isAnswered)).findFirst().get();
            Movie movieA = movieService.getMovie(movies.getMovieA());
            Movie movieB = movieService.getMovie(movies.getMovieB());

            return Arrays.asList(movieA, movieB);
        }

        Pair<Movie, Movie> pairMovies = generateNewRandomMoviePair(setPairMovies);
        registerSortedMovies(game, pairMovies);
        return Arrays.asList(pairMovies.getLeft(), pairMovies.getRight());
    }



    @Override
    public GameRoundDTO hit(User user, String imdbID) {
        final GameRound game = getAvaliableGameFor(user);
        GameRoundDTO gameRoundDTO = doHit(game, imdbID);

        gameRoundDTO.setPoints(game.getPoint());
        gameRoundDTO.setWrongs(game.getWrong());

        if (gameRoundDTO.isRightHit()) {
            game.addPoint();
            gameRoundDTO.setPoints(game.getPoint());
        } else {
            game.addWrong();
            gameRoundDTO.setWrongs(game.getWrong());

            //TODO mostrar uma mensagem que perdeu o jogo
            if (game.getWrong() >= 3) {
                gameRoundDTO.setGameOver(true);
                game.setStatus(GameStatus.END);
            }

        }

        repository.save(game);

        return gameRoundDTO;
    }

    private Pair<Movie, Movie> generateNewRandomMoviePair(Set<String> setPairMovies) {
        Movie movieA = movieService.getRandomMovie();
        Movie movieB = movieService.getRandomMovie();

        int retryQtd = 0;

        while (movieB.equals(movieA) || setPairMovies.contains(movieA.getImdbID() + movieB.getImdbID())) {
            retryQtd++;
            if (retryQtd > 10) {
                //TODO criar um erro personalizado
                throw new UnsupportedOperationException("");
            }
            movieB = movieService.getRandomMovie();
        }
        return Pair.of(movieA, movieB);
    }

    private void registerSortedMovies(GameRound game, Pair<Movie, Movie> pairMovies) {
        PairMovies entityPairMovies = PairMovies.builder()
                .movieA(pairMovies.getLeft().getImdbID())
                .movieB(pairMovies.getRight().getImdbID())
                .answered(false)
                .gameRound(game)
                .build();

        entityPairMovies = pairMoviesRepository.save(entityPairMovies);

        List<PairMovies> listPairMovies = game.getPairMovies() == null ? new ArrayList<>() : game.getPairMovies();

        listPairMovies.add(entityPairMovies);
        game.setPairMovies(listPairMovies);

        repository.save(game);
    }

    private Set<String> previusSoterdPairMovies(GameRound game) {
        final Set<String> setPairMovies = new HashSet<>();

        game.getPairMovies().stream().forEach(pairMovies -> {
            setPairMovies.add(pairMovies.getMovieA() + pairMovies.getMovieB());
            setPairMovies.add(pairMovies.getMovieB() + pairMovies.getMovieA());
        });
        return setPairMovies;
    }

    private GameRound getAvaliableGameFor(User user) {
        Optional<GameRound> inProgressGames = repository.findGamesFromUser(new GameStatus[]{GameStatus.READY, GameStatus.WAITING_HIT}, user);

        if (!inProgressGames.isPresent()) {
            throw new IllegalStateException("Game not avaliable");
        }

        return inProgressGames.get();
    }

    private boolean hasNotAnswredMovie(GameRound game) {
        return game.getPairMovies().stream().anyMatch(Predicate.not(PairMovies::isAnswered));
    }


    private GameRoundDTO doHit(GameRound game, String imdbID) {
        if (!hasNotAnswredMovie(game)) {
            throw new IllegalStateException("");
        }

        PairMovies pairMovies = game.getPairMovies().stream().filter(Predicate.not(PairMovies::isAnswered)).findFirst().get();

        Movie movieA = movieService.getImdbDataMovie(pairMovies.getMovieA());
        Movie movieB = movieService.getImdbDataMovie(pairMovies.getMovieB());

        boolean isRightHit =
                imdbID.trim().equalsIgnoreCase(movieA.getImdbRating().compareTo(movieB.getImdbRating()) > 0 ? movieA.getImdbID().trim() : movieB.getImdbID().trim())
                || movieA.getImdbRating().compareTo(movieB.getImdbRating()) == 0;

        pairMovies.setAnswered(true);
        pairMoviesRepository.save(pairMovies);

        return GameRoundDTO.builder()
                .movieA(movieA)
                .movieB(movieB)
                .gameOver(false)
                .points(0)
                .wrongs(0)
                .isRightHit(isRightHit).build();
    }
}
