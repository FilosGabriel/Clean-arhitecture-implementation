package com.filos.user;

import java.util.List;

import com.filos.entity.User;
import com.filos.exception.UserNotFoundException;
import com.filos.port.UserRepository;

public class FindUser {
    private final UserRepository repository;

    public FindUser(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(final String id) {
        return repository.findById(id)
                         .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }
}
