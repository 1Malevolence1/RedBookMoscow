package com.example.server_part_service.controller;

import com.example.server_part_service.dto.entry.RequestEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTO;
import com.example.server_part_service.model.EntryModel;
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

    @GetMapping
    public ResponseEntity<ResponseEntryDTO> get(long entryId) {
        return new ResponseEntity<>(service.getDtoById(entryId), HttpStatus.valueOf(200));
    }
    @PostMapping
    public ResponseEntity<ResponseEntryDTO> post(@RequestBody RequestEntryDTO dto) {
        ResponseEntryDTO responseEntryDTO = service.converterModelToResponseDto(service.saveModel(service.converterDtoToModel(dto)));
        return new ResponseEntity<>(responseEntryDTO, HttpStatus.valueOf(201));
    }
    @PutMapping
    public ResponseEntity<ResponseEntryDTO> put(RequestEntryDTO dto) {
        ResponseEntryDTO responseEntryDTO = service.converterModelToResponseDto(service.updateModel(service.converterDtoToModel(dto)));
        return new ResponseEntity<>(responseEntryDTO, HttpStatus.valueOf(202));
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(long entryId) {
        service.deleteEntry(entryId);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
