package com.battle.movie.battle.service.impl;

import com.battle.movie.battle.enums.UserStatus;
import com.battle.movie.battle.model.User;
import com.battle.movie.battle.model.dto.RankingDTO;
import com.battle.movie.battle.repository.GameRoundRepository;
import com.battle.movie.battle.repository.UserRepository;
import com.battle.movie.battle.service.IRankingService;
import com.battle.movie.battle.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserStatus register(User newUser) {
        return null;
    }

    @Override
    public UserStatus login(User user) {
        return null;
    }

    @Override
    public UserStatus logout(User user) {
        return null;
    }
}
