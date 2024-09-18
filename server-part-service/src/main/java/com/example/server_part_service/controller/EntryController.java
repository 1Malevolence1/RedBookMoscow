package com.example.server_part_service.controller;

import com.example.server_part_service.dto.entry.RequestEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTO;
import com.example.server_part_service.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/server-part/entry")
public class EntryController {

    @Autowired
    private EntryService service;


    @GetMapping("/all")
    public ResponseEntity<List<ResponseEntryDTO>> getListEntryForMainPage(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{entryId:\\d+}")
    public ResponseEntity<ResponseEntryDTO> get(@PathVariable(name = "entryId") Long entryId) {
        return new ResponseEntity<>(service.getDtoById(entryId), HttpStatus.valueOf(200));
    }
    @PostMapping
    public ResponseEntity<?> post( @RequestBody RequestEntryDTO dto) {
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
