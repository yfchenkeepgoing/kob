package com.kob.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("20001017"));
        // System.out.println(passwordEncoder.matches("20001017", "$2a$10$R7jdyVRqH7jcVQ2itauIQu6QO.Fwkwwz0T57yElvPCKExKmP.dkq6"));
        System.out.println(passwordEncoder.encode("20001015"));
        System.out.println(passwordEncoder.encode("201867"));
        System.out.println(passwordEncoder.encode("pbb"));
    }
}
