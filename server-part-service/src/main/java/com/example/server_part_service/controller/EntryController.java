package com.example.server_part_service.controller;

import com.example.server_part_service.dto.entry.RequestEntryDTO;
import com.example.server_part_service.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/entry")
public class EntryController {

    @Autowired
    private EntryService service;

//    @GetMapping
//    public ResponseEntity<RequestEntryDTO> get(long entryId) {
//
//        return new ResponseEntity<>(service.getDtoById(entryId), HttpStatus.valueOf(200));
//    }
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
