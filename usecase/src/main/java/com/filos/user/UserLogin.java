package com.filos.user;

import com.filos.entity.User;
import com.filos.exception.NotAllowedException;
import com.filos.port.PasswordEncoder;
import com.filos.port.UserRepository;
import com.filos.requests.LoginRequests;

public class UserLogin {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserLogin(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User login(final LoginRequests loginCredentials) {
        User user = userRepository.findByEmail(loginCredentials.getEmail())
                                  .orElseThrow(NotAllowedException::new);
        checkPassword(loginCredentials.getPassword(), user);
        return user;
    }

    private void checkPassword(String password, User user) {
        if (!user.getEmail().equals(encoder.encodePassword(password))) {
            throw new NotAllowedException();
        }
    }
}
