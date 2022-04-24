package com.battle.movie.battle.repository;

import com.battle.movie.battle.model.GameRound;
import com.battle.movie.battle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}