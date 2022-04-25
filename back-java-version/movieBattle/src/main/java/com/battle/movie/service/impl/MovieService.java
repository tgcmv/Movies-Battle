package com.battle.movie.service.impl;

import com.battle.movie.core.OMDBAPIAccess;
import com.battle.movie.model.Movie;
import com.battle.movie.repository.MovieRepository;
import com.battle.movie.service.IMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class MovieService implements IMovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository movieRepository) {
        this.repository = movieRepository;
    }

    @Override
    public void populateMovies(MultipartFile multipartFile) throws IOException {

        OMDBAPIAccess api = OMDBAPIAccess.getInstance();

        InputStream inputStream = multipartFile.getInputStream();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .forEach(
                        line -> {
                            try {
                                repository.save(api.getMovie(line));
                            } catch (IOException e) {
                                log.error("error to get movies", e);
                            }
                        }
                );

    }

    @Override
    public Movie getMovie(String movieId) {
        return repository.findByImdbID(movieId).get();
    }

    @Override
    public BigDecimal getScoreMovie(String movieID) {
        OMDBAPIAccess api = OMDBAPIAccess.getInstance();
        try {
            Movie movie = api.getMovie(movieID);
            return movie.getImdbRating();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Cacheable
    public Movie getRandomMovie(){
        Random rand = new Random();
        List<Movie> movies = listOfMovies();
        return movies.get(rand.nextInt(movies.size()));
    }

    @Cacheable
    private List<Movie> listOfMovies(){
        return repository.findAll();
    }

}
