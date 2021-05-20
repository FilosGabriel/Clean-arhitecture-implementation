package com.filos.port;

import com.filos.entity.User;
import jakarta.validation.constraints.NotBlank;

public interface IdGenerator {
    @NotBlank
    String generateId();

    @NotBlank
    User generateId(final User user);
}
