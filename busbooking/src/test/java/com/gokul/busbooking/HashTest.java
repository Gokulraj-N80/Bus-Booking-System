package com.gokul.busbooking;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashTest {
    @Test
    public void testHash() {
        System.out.println("HASH_IS:" + new BCryptPasswordEncoder().encode("pasword123"));
    }
}
