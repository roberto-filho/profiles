package com.filho.profiles.controllers;

import com.filho.util.LoggedInUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/session")
public class SessionController {

    @GetMapping("/user")
    public ResponseEntity getLoggedInUser() {
        return ResponseEntity.ok(LoggedInUser.getInstance());
    }

}
