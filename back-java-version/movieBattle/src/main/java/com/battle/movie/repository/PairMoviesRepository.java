package com.battle.movie.repository;

import com.battle.movie.core.GameStatus;
import com.battle.movie.model.GameRound;
import com.battle.movie.model.PairMovies;
import com.battle.movie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PairMoviesRepository extends JpaRepository<PairMovies, Long>{

}
