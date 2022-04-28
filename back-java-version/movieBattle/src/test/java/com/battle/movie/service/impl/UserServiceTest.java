package com.battle.movie.service.impl;

import com.battle.movie.model.dto.LoginForm;
import com.battle.movie.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void registerUser() {
        LoginForm loginForm = new LoginForm();
        loginForm.setName("name");
        loginForm.setPassword("password");
        service.register(loginForm);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());

    }

}
