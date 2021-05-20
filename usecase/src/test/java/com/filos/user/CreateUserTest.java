package com.filos.user;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.filos.entity.User;
import com.filos.exception.UserAlreadyExistsException;
import com.filos.mapper.UserMapper;
import com.filos.port.UserRepository;

@DisplayName("When creating a user")
@ExtendWith(MockitoExtension.class)
class CreateUserTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;

    @Test
    @DisplayName("It should fail if it found")
    void createFail() {
        // Given
        CreateUser createUser = new CreateUser(userRepository, userMapper);
        User user = User.builder()
                        .email("someEmail@example.com")
                        .build();
        when(userRepository.findByEmail("someEmail@example.com"))
                .thenReturn(Optional.empty());
        // When
        thenThrownBy(() -> createUser.create(user))
                // Then
                .isInstanceOf(UserAlreadyExistsException.class);
    }

    @Test
    @DisplayName("it should success if it not found")
    void createSuccessfully() {

        CreateUser createUser = new CreateUser(userRepository, userMapper);
        User user = User.builder()
                        .email("someEmail@example.com")
                        .password("password")
                        .build();
        var email = "someEmail@example.com";

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));
        when(userRepository.save(user))
                .thenReturn(user);
        when(userMapper.mapEncoded(user))
                .thenReturn(user);

        User createdUser = createUser.create(user);
        then(createdUser.getPassword())
                .isNotEqualTo("password2");
        Mockito.verify(userRepository, times(1))
               .findByEmail(email);
        Mockito.verify(userRepository, times(1))
               .save(user);
    }

}