package com.filos.uuid;

import com.filos.entity.User;
import com.filos.port.IdGenerator;
import com.filos.port.PasswordEncoder;

public class UserMapper implements com.filos.mapper.UserMapper {
    private final PasswordEncoder encoder;
    private final IdGenerator idGenerator;

    public UserMapper(PasswordEncoder encoder, IdGenerator idGenerator) {
        this.encoder = encoder;
        this.idGenerator = idGenerator;
    }

    @Override
    public User mapEncoded(User user) {
        return User.builder()
                   .email(user.getEmail())
                   .firstName(user.getFirstName())
                   .lastName(user.getLastName())
                   .password(encoder.encodePassword(user.getPassword()))
                   .id(idGenerator.generateId())
                   .build();
    }
}
