package com.example.authenticationservice.user;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class KeycloakUserTest {

    @Test
    void testBuilder(){
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("atr1", "Attribute 01");
        attributes.put("atr2", "Attribute 02");
        attributes.put("atr3", "Attribute 03");
        attributes.put("atr4", "Attribute 04");
        KeycloakUser keycloakUser = KeycloakUser.builder()
                .username("hongluyen01")
                .enabled(true)
                .attributes(attributes)
                .build();
        System.out.println(keycloakUser.toString());
    }
}