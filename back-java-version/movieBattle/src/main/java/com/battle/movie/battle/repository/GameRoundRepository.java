package com.battle.movie.battle.repository;

import com.battle.movie.battle.model.GameRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRoundRepository  extends JpaRepository<GameRound, String>, JpaSpecificationExecutor<GameRound> {

}
