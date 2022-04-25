package com.battle.movie.service.impl;

<<<<<<< HEAD:back-java-version/movieBattle/src/main/java/com/battle/movie/service/impl/MovieService.java
import com.battle.movie.core.OMDBAPIAccess;
import com.battle.movie.repository.MovieRepository;
import com.battle.movie.service.IMovieService;
=======
import com.battle.movie.battle.core.OMDBAPIAccess;
import com.battle.movie.battle.repository.MovieRepository;
import com.battle.movie.battle.service.IMovieService;
>>>>>>> b85b6d164e76689b86d8b9036c7978a6fe634f8d:back-java-version/movieBattle/src/main/java/com/battle/movie/battle/service/impl/MovieService.java
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
