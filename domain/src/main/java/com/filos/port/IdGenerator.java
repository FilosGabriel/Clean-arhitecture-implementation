package com.filos.port;

import com.filos.entity.User;

public interface IdGenerator {
    String generateId();

    User generateId(final User user);
}
