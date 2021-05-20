package com.filos.user;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.filos.entity.User;
import com.filos.exception.UserNotFoundException;
import com.filos.port.UserRepository;

@DisplayName("When finding a user")
@ExtendWith(MockitoExtension.class)
class FindUserTest {

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("it should find the user")
    public void findUser() {
        // Given
        FindUser findUser = new FindUser(userRepository);
        User user = User.builder()
                        .email("someEmail@example.com")
                        .build();
        // When
        when(userRepository.findById("id"))
                .thenReturn(Optional.of(user));
        User actual = findUser.findById("id");
        then(actual.getId()).isEqualTo("id");
        // Then
    }

    @Test
    @DisplayName("it shouldn't find the user")
    public void notFindUser() {
        // Given
        FindUser findUser = new FindUser(userRepository);
        // When
        when(userRepository.findById("id"))
                .thenReturn(Optional.empty());
        thenThrownBy(() -> findUser.findById("id"))
                .isInstanceOf(UserNotFoundException.class);
        // Then
    }

    @Test
    @DisplayName("it should get all users")
    public void allUsers() {
        // Given
        FindUser findUser = new FindUser(userRepository);
        User user = User.builder()
                        .email("someEmail@example.com")
                        .build();
        // When
        when(userRepository.findAll())
                .thenReturn(List.of());
        List<User> actual = findUser.findAllUsers();
        then(actual).hasSize(0);
        // Then
    }

}