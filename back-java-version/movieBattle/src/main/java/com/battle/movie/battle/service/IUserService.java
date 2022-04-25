package com.battle.movie.battle.service;

import com.battle.movie.battle.enums.UserStatus;
import com.battle.movie.battle.model.User;

public interface IUserService {
    UserStatus register(User newUser);

    UserStatus login(User user);

    UserStatus logout(User user);
}
