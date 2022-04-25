package com.battle.movie.service;

import com.battle.movie.model.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

public interface IMovieService {
    void populateMovies(MultipartFile multipartFile) throws IOException;

    Movie getMovie(String movieA);

    BigDecimal getScoreMovie(String movieA);
}
