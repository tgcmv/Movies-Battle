package com.battle.movie.battle.service.impl;

import com.battle.movie.battle.core.OMDBAPIAccess;
import com.battle.movie.battle.repository.MovieRepository;
import com.battle.movie.battle.service.IMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
}
