package com.filos.user;

import com.filos.entity.User;
import com.filos.exception.UserAlreadyExistsException;
import com.filos.mapper.UserMapper;
import com.filos.port.UserRepository;
import com.filos.port.ValidUser;

public class CreateUser {
    private final UserRepository repository;
    private final UserMapper mapper;

    public CreateUser(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public User create(@ValidUser final User user) {
        repository.findByEmail(user.getEmail())
                  .orElseThrow(UserAlreadyExistsException::new);
        User mappedUser = mapper.mapEncoded(user);
        return repository.save(mappedUser);
    }
}

