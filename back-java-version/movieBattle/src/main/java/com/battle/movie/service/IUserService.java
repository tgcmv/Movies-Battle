package com.battle.movie.service;

import com.battle.movie.enums.UserStatus;
import com.battle.movie.model.User;

public interface IUserService {
    UserStatus register(User newUser);

    UserStatus login(User user);

    UserStatus logout(User user);
}
