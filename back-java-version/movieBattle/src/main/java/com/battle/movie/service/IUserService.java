package com.battle.movie.service;

import com.battle.movie.model.dto.LoginForm;
import com.battle.movie.model.dto.RankingDTO;

import java.util.Collection;

public interface IUserService {

    void register(LoginForm form);
}
