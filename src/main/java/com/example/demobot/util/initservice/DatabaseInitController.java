package com.example.demobot.util.initservice;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/init-db")
@RequiredArgsConstructor
public class DatabaseInitController {

    private final DatabaseInitService databaseInitService;

    @PostMapping
    public ResponseEntity<?> initDatabase() {
        databaseInitService.initPromocodes();
        return ResponseEntity.ok(
                " ok ");
    }
}
