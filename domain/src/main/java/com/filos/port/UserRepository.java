package com.filos.port;

import java.util.List;
import java.util.Optional;

import com.filos.entity.User;

public interface UserRepository {
    Optional<User> findById(String id);

    List<User> findAl();

    void save(User user);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
