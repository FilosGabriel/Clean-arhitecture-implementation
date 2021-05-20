package com.filos.user;

import static org.assertj.core.api.BDDAssertions.thenCode;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.filos.entity.User;
import com.filos.exception.NotAllowedException;
import com.filos.port.PasswordEncoder;
import com.filos.port.UserRepository;
import com.filos.requests.LoginRequests;

@ExtendWith(MockitoExtension.class)
@DisplayName("When user try to log in it ")
class UserLoginTest {
    String email = "someEmail@example.com";
    String encodedPassword = "encodedPassword";
    String password = "password";

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder encoder;

    @Test
    @DisplayName("should allow to loging")
    public void allowLogin() {
        // Given

        User user = User
                .builder()
                .email(email)
                .password(encodedPassword)
                .build();
        UserLogin userLogin = new UserLogin(userRepository, encoder);
        // When
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));
        when(encoder.encodePassword(password))
                .thenReturn(encodedPassword);
        // Then
        thenCode(() -> userLogin.login(new LoginRequests(email, password)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("it should not found")
    public void notFoundUser() {
        // Given
        UserLogin userLogin = new UserLogin(userRepository, encoder);

        // When
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.empty());
        // Then
        thenCode(() -> userLogin.login(new LoginRequests(email, password)))
                .isInstanceOf(NotAllowedException.class);
    }

    @Test
    @DisplayName("it should fail to login")
    public void failToLogin() {
        // Given
        UserLogin userLogin = new UserLogin(userRepository, encoder);
        User user = User
                .builder()
                .email(email)
                .password(encodedPassword)
                .build();

        // When
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));
        when(encoder.encodePassword(password))
                .thenReturn("otherEncodedPassword");
        // Then
        thenCode(() -> userLogin.login(new LoginRequests(email, password)))
                .isInstanceOf(NotAllowedException.class);

        // Then
    }
}