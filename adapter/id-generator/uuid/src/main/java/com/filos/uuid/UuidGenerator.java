package com.filos.uuid;

import java.util.UUID;

import com.filos.entity.User;
import com.filos.port.IdGenerator;

public class UuidGenerator implements IdGenerator {
    @Override
    public String generateId() {
        return UUID.randomUUID()
                   .toString();
    }

    @Override
    public User generateId(User user) {
        user.setId(generateId());
        return user;
    }
}
