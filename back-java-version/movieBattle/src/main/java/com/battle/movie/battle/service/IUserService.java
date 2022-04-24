package com.battle.movie.battle.service;

import com.battle.movie.battle.enums.UserStatus;
import com.battle.movie.battle.model.User;
import com.battle.movie.battle.model.dto.RankingDTO;

import java.util.Collection;

public interface IUserService {
    UserStatus register(User newUser);

    UserStatus login(User user);

    UserStatus logout(User user);
}
