package com.battle.movie.battle.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IMovieService {
    void populateMovies(MultipartFile multipartFile) throws IOException;
}
