package com.battle.movie.repository;

import com.battle.movie.core.GameStatus;
import com.battle.movie.model.GameRound;
import com.battle.movie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRoundRepository  extends JpaRepository<GameRound, Long>{
    @Query("SELECT g FROM GameRound g WHERE g.status IN (?1) AND g.user = ?2 ")
    Optional<GameRound> findGamesFromUser(GameStatus[] status, User user);

}
