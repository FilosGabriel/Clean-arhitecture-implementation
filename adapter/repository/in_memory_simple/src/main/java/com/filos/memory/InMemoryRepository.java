package com.filos.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.filos.entity.User;
import com.filos.port.UserRepository;

public class InMemoryRepository implements UserRepository {
    private final Map<String, User> users;

    public InMemoryRepository() {
        users = new HashMap<>();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values()
                    .stream()
                    .filter(user -> user.getEmail()
                                        .equals(email))
                    .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return findByEmail(username).stream()
                                    .filter(user -> user.getPassword()
                                                        .equals(password))
                                    .findFirst();
    }
}
