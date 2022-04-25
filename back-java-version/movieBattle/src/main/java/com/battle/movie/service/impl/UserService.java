<<<<<<< HEAD:back-java-version/movieBattle/src/main/java/com/battle/movie/service/impl/UserService.java
package com.battle.movie.service.impl;

import com.battle.movie.enums.UserStatus;
import com.battle.movie.model.User;
import com.battle.movie.repository.UserRepository;
import com.battle.movie.service.IUserService;
=======
package com.battle.movie.battle.service.impl;

import com.battle.movie.battle.enums.UserStatus;
import com.battle.movie.battle.model.User;
import com.battle.movie.battle.repository.UserRepository;
import com.battle.movie.battle.service.IUserService;
>>>>>>> b85b6d164e76689b86d8b9036c7978a6fe634f8d:back-java-version/movieBattle/src/main/java/com/battle/movie/battle/service/impl/UserService.java
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
