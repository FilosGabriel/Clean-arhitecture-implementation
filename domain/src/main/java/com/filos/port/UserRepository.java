package com.filos.port;

import java.util.List;
import java.util.Optional;

import com.filos.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface UserRepository {
    Optional<User> findById(@NotBlank final String id);

    Optional<User> findByEmail(@NotBlank final String Email);

    List<User> findAll();

    User save(@NotNull User user);

    Optional<User> findByUsernameAndPassword(@NotBlank final String username, @NotBlank String password);
}
