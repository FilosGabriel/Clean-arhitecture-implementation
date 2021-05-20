package com.filos.sha;

import org.apache.commons.codec.digest.DigestUtils;

import com.filos.port.PasswordEncoder;

public class SHA256Encoder implements PasswordEncoder {
    @Override
    public String encodePassword(String plainTextPassword) {
        return DigestUtils.sha256Hex(plainTextPassword);
    }
}
