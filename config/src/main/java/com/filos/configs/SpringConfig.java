package com.filos.configs;

import com.filos.hazelcast.HazelcastRepository;
import com.filos.mapper.UserMapper;
import com.filos.port.IdGenerator;
import com.filos.port.PasswordEncoder;
import com.filos.port.UserRepository;
import com.filos.sha.SHA256Encoder;
import com.filos.user.CreateUser;
import com.filos.user.FindUser;
import com.filos.user.UserLogin;
import com.filos.uuid.UuidGenerator;

public class SpringConfig {
    private final UserRepository userRepository = new HazelcastRepository();
    private final PasswordEncoder passwordEncoder = new SHA256Encoder();
    private final IdGenerator generator = new UuidGenerator();
    private final UserMapper mapper = new com.filos.uuid.UserMapper(passwordEncoder, generator);

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
