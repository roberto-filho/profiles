package com.filho.profiles.controllers;

import com.filho.profiles.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filters")
public class FiltersController {

    private final ProfileService service;

    @Autowired
    public FiltersController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/religion")
    public ResponseEntity<List<String>> getReligions() {
        return ResponseEntity
                .ok(service.findDistinctReligions());
    }

}
