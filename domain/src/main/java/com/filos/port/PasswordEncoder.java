package com.filos.port;

import jakarta.validation.constraints.NotNull;

public interface PasswordEncoder {

    @NotNull String encodePassword(@NotNull String plainTextPassword);

}
