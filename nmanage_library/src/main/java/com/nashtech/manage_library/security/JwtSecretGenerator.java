package com.nashtech.manage_library.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.UUID;

public class JwtSecretGenerator {
    public static String generateSecret() {
        // Generate a random UUID
        String randomUuid = UUID.randomUUID().toString();

        // Encode the UUID using BCryptPasswordEncoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(randomUuid);
    }
}
