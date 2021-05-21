package com.filos.configs;

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

public class ManualConfig {
    private final UserRepository userRepository = new InMemoryRepository();
    private final IdGenerator idGenerator = new UuidGenerator();
    private final PasswordEncoder passwordEncoder = new SHA256Encoder();

    private final UserMapper mapper = new com.filos.uuid.UserMapper(passwordEncoder, idGenerator);

    public CreateUser createUser() {
        return new CreateUser(userRepository, mapper);
    }

    public FindUser findUser() {
        return new FindUser(userRepository);
    }

    public UserLogin loginUser() {
        return new UserLogin(userRepository, passwordEncoder);
    }
}
