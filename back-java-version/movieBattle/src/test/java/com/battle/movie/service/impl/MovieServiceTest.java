package com.battle.movie.service.impl;

import com.battle.movie.model.Movie;
import com.battle.movie.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    public static final String MOVIE = "movie";
    @Mock
    private MovieRepository repository;

    @InjectMocks
    private MovieService service;

    @Test
    void getMovie() {
        Mockito.when(repository.findByImdbID(MOVIE)).thenReturn(Optional.of(Movie.builder().build()));
        service.getMovie(MOVIE);
        Mockito.verify(repository, Mockito.times(1)).findByImdbID(MOVIE);
    }

    @Test
    void getRandomMovie() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(Movie.builder().build()));
        service.getRandomMovie();
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

}
