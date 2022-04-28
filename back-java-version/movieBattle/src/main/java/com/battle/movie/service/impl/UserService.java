package com.battle.movie.service.impl;

import com.battle.movie.model.User;
import com.battle.movie.model.dto.LoginForm;
import com.battle.movie.repository.UserRepository;
import com.battle.movie.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public void register(LoginForm form) {
        User user = User.builder().name(form.getName()).password(new BCryptPasswordEncoder().encode(form.getPassword())).build();
        repository.save(user);
    }
}
