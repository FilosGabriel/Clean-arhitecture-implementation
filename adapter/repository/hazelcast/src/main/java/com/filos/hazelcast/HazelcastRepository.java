package com.filos.hazelcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.filos.entity.User;
import com.filos.port.UserRepository;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.query.impl.predicates.SqlPredicate;

public class HazelcastRepository implements UserRepository {
    private final HazelcastInstance instance = HazelcastFactory.getInstance();
    private static final String MAP_NAME = "users";

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(instance.<String, User>getMap(MAP_NAME)
                                           .get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return instance.<String, User>getMap(MAP_NAME)
                       .values(new SqlPredicate(" email='%s'".formatted(email)))
                       .stream()
                       .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(instance.<String, User>getMap(MAP_NAME)
                                       .values());
    }

    @Override
    public User save(User user) {
        return instance.<String, User>getMap(MAP_NAME)
                       .put(user.getId(), user);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        var predicate = "email='%s' AND password='%s'".formatted(username, password);
        return instance.<String, User>getMap(MAP_NAME)
                       .values(new SqlPredicate(predicate))
                       .stream()
                       .findFirst();
    }
}
