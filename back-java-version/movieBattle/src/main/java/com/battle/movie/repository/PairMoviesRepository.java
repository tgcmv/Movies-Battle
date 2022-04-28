package com.battle.movie.repository;

import com.battle.movie.model.PairMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PairMoviesRepository extends JpaRepository<PairMovies, Long>{

}
