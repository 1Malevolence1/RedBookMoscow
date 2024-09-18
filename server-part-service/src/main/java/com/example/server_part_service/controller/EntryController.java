package com.example.server_part_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/entry")
public class EntryController {

    @GetMapping
    public ResponseEntity<Void> get() {
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }
    @PostMapping
    public ResponseEntity<Void> post() {
        return new ResponseEntity<>(HttpStatus.valueOf(201));
    }
    @PutMapping
    public ResponseEntity<Void> put() {
        return new ResponseEntity<>(HttpStatus.valueOf(202));
    }
    @DeleteMapping
    public ResponseEntity<Void> delete() {
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
