package com.battle.movie.repository;

import com.battle.movie.model.Movie;
import com.battle.movie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Movie> {

    Optional<Movie> findByImdbID(String imdbID);

}
