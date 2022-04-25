package com.battle.movie.service.impl;

import com.battle.movie.core.GameStatus;
import com.battle.movie.model.GameRound;
import com.battle.movie.model.Movie;
import com.battle.movie.model.PairMovies;
import com.battle.movie.model.User;
import com.battle.movie.repository.GameRoundRepository;
import com.battle.movie.repository.PairMoviesRepository;
import com.battle.movie.service.IGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public List<Movie> nextRound(User user) {

        Set<String> setPairMovies = new HashSet<>();

        GameRound game;
        Optional<GameRound> gamesFromUser = repository.findGamesFromUser(GameStatus.WAITING_HIT, user);

        if(gamesFromUser.isPresent()){
            game = gamesFromUser.get();
            List<PairMovies> lPairMovies = gamesFromUser.get().getPairMovies();
            lPairMovies.stream().forEach(pairMovies -> {
                setPairMovies.add(pairMovies.getMovieA() + pairMovies.getMovieB());
                setPairMovies.add(pairMovies.getMovieB() + pairMovies.getMovieA());
            });

            if(hasNotAnswredMovie(gamesFromUser)){
                Optional<PairMovies> first = gamesFromUser.get().getPairMovies().stream().filter(Predicate.not(PairMovies::isAnswered)).findFirst();

                Movie movieA = movieService.getMovie(first.get().getMovieA());
                Movie movieB = movieService.getMovie(first.get().getMovieB());

                return Arrays.asList(movieA, movieB);
            };


        } else {
            game = GameRound.builder()
                    .status(GameStatus.WAITING_HIT)
                    .user(user)
                    .point(0)
                    .wrong(0)
                    .build();

            game = repository.save(game);
        }

        Movie movieA = movieService.getRandomMovie();
        Movie movieB = movieService.getRandomMovie();

        int retryQtd = 0;

        while(movieB.equals(movieA) || setPairMovies.contains(movieA.getImdbID() + movieB.getImdbID())){
            retryQtd++;
            if(retryQtd > 10){
                //TODO criar um erro personalizado
                throw new UnsupportedOperationException("");
            }

            movieB = movieService.getRandomMovie();
        }

        PairMovies entityPairMovies = PairMovies.builder()
                .movieA(movieA.getImdbID())
                .movieB(movieB.getImdbID())
                .answered(false)
                .gameRound(game)
                .build();

        pairMoviesRepository.save(entityPairMovies);

        List<PairMovies> pairMovies = game.getPairMovies() == null ? new ArrayList<>() : game.getPairMovies();

        pairMovies.add(entityPairMovies);
        game.setPairMovies(pairMovies);

        repository.save(game);

        return Arrays.asList(movieA, movieB);
    }

    private boolean hasNotAnswredMovie(Optional<GameRound> gamesFromUser) {
        return gamesFromUser.get().getPairMovies().stream().anyMatch(Predicate.not(PairMovies::isAnswered));
    }

    @Override
    public boolean hit(User user, String imdbID) {
        Optional<GameRound> gamesFromUser = repository.findGamesFromUser(GameStatus.WAITING_HIT, user);
        boolean hit = validatedHit(gamesFromUser, imdbID);
        if(hit){
            gamesFromUser.get().addPoint();
        } else {
            gamesFromUser.get().addWrong();

            //TODO mostrar uma mensagem que perdeu o jogo
            if(gamesFromUser.get().getWrong() >= 3){
                gamesFromUser.get().setStatus(GameStatus.END);
            }

        }

        repository.save(gamesFromUser.get());

        return hit;
    }

    private boolean validatedHit(Optional<GameRound> gamesFromUser, String imdbID) {
        if(gamesFromUser.isEmpty()){
            //TODO mensagem
            throw new IllegalStateException("");
        }

        if(!hasNotAnswredMovie(gamesFromUser)){
            //TODO mensagem
            throw new IllegalStateException("");
        }

        PairMovies pairMovies = gamesFromUser.get().getPairMovies().stream().filter(Predicate.not(PairMovies::isAnswered)).findFirst().get();
        boolean b = imdbID.equalsIgnoreCase(getIdMovieWithMaxScore(pairMovies));
        pairMovies.setAnswered(true);
        pairMoviesRepository.save(pairMovies);
        return b;
    }

    protected String getIdMovieWithMaxScore( PairMovies pairMovies) {
        BigDecimal scoreA = movieService.getScoreMovie(pairMovies.getMovieA());
        BigDecimal scoreB = movieService.getScoreMovie(pairMovies.getMovieB());
        return scoreA.compareTo(scoreB) > 1 ? pairMovies.getMovieA() : pairMovies.getMovieB();
    }
}
