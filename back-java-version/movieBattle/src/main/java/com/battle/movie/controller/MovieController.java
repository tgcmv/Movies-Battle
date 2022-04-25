package com.battle.movie.controller;

import com.battle.movie.service.IMovieService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {

    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @ApiOperation("popular base com lista de filmes baseado no id do imdb")
    @PostMapping("/populate")
    public ResponseEntity<Void> populateMovies(@RequestPart(value = "file") MultipartFile multipartFile) throws IOException {
        log.info("m=populateMovies, multipartFile={}", multipartFile.getOriginalFilename());
        movieService.populateMovies(multipartFile);

        return ResponseEntity.ok().build();
    }
}
