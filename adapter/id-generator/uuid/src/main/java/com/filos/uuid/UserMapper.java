package com.filos.uuid;

import com.filos.entity.User;
import com.filos.port.PasswordEncoder;

public class UserMapper implements com.filos.mapper.UserMapper {
    private final PasswordEncoder encoder;

    public UserMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User mapEncoded(User user) {
        return User.builder()
                   .email(user.getEmail())
                   .firstName(user.getFirstName())
                   .lastName(user.getLastName())
                   .password(encoder.encodePassword(user.getPassword()))
                   .id(user.getId())
                   .build();
    }
}
