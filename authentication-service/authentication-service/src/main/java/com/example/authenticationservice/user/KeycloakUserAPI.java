package com.example.authenticationservice.user;

import com.example.authenticationservice.credential.KeycloakAccessToken;
import com.example.authenticationservice.util.Peggy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/users")
public class KeycloakUserAPI {

    @Autowired
    KeycloakService keycloakService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Peggy<KeycloakUser>> index() throws IOException {
        return new ResponseEntity<Peggy<KeycloakUser>>(
                keycloakService.findAll(null, null),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<KeycloakUser> register(@RequestBody KeycloakUser keycloakUser) throws IOException {
        if (keycloakService.save(keycloakUser)) {
            return new ResponseEntity<KeycloakUser>(keycloakUser, HttpStatus.CREATED);
        } else {
            throw new IOException();
        }
    }
}
