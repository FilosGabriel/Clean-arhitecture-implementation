package com.filos;

import com.filos.mapper.UserMapper;
import com.filos.memory.InMemoryRepository;
import com.filos.port.IdGenerator;
import com.filos.port.PasswordEncoder;
import com.filos.port.UserRepository;
import com.filos.sha.SHA256Encoder;
import com.filos.user.CreateUser;
import com.filos.user.FindUser;
import com.filos.user.UserLogin;
import com.filos.uuid.UuidGenerator;

public class VertxConfig {
    private final UserRepository userRepository = new InMemoryRepository();
    private final IdGenerator idGenerator = new UuidGenerator();
    private final PasswordEncoder passwordEncoder = new SHA256Encoder();
    private final UserMapper mapper = new com.filos.uuid.UserMapper(passwordEncoder);
    private final CreateUser createUser = new CreateUser(userRepository, mapper);
    private final FindUser findUser = new FindUser(userRepository);
    private final UserLogin loginUser = new UserLogin(userRepository, passwordEncoder);

    public CreateUser createUser() {
        return createUser;
    }

    public FindUser findUser() {
        return findUser;
    }

    public UserLogin loginUser() {
        return loginUser;
    }
}
