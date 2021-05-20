package com.filos.mapper;

import com.filos.entity.User;
import jakarta.validation.constraints.NotNull;

public interface UserMapper {

    @NotNull
    User mapEncoded(@NotNull final User user);
}
