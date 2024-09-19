package com.example.server_part_service.controller;

import com.example.server_part_service.dto.entry.RequestEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTOFourFields;
import com.example.server_part_service.exception.AlreadyExistException;
import com.example.server_part_service.exception.DeleteEntryException;
import com.example.server_part_service.exception.EntryNotFoundException;
import com.example.server_part_service.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/server-part/entry")
@Slf4j
public class EntryController {

    @Autowired
    private EntryService service;

    @GetMapping("/all")
    public ResponseEntity<List<ResponseEntryDTOFourFields>> getListEntryForMainPage() {
        return ResponseEntity.ok().body(service.findAllPreview());
    }

    @GetMapping("/all-with-all-params")
    public ResponseEntity<List<ResponseEntryDTO>> getListEntryAllData() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{entryId:\\d+}")
    public ResponseEntity<ResponseEntryDTO> get(@PathVariable(name = "entryId") Long entryId) {
        return new ResponseEntity<>(service.getDtoById(entryId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseEntryDTO> post(@RequestBody RequestEntryDTO dto) {
        try {
            log.info("СМОТРИ СЮДА ->>>>>>>>>>>>>>>>>{}", dto);
            ResponseEntryDTO responseEntryDTO = service.converterModelToResponseDto(service.saveModel(service.converterDtoToModel(dto)));
            return new ResponseEntity<>(responseEntryDTO, HttpStatus.OK);
        } catch (AlreadyExistException e) {
            log.warn("\njust exist!!!");
        } catch (Exception e) {
            log.warn("\n--------------\nerror in post entry!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<ResponseEntryDTO> put(@RequestBody RequestEntryDTO dto) {
        ResponseEntryDTO responseEntryDTO = null;
        try {
            responseEntryDTO = service.converterModelToResponseDto(service.updateModel(service.converterDtoToModel(dto)));
        } catch (EntryNotFoundException e) {
            log.warn("\nerror in update");
        }
        return new ResponseEntity<>(responseEntryDTO, HttpStatus.valueOf(200));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{entryId:\\d+}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "entryId") Long entryId) {
        try {
            service.deleteEntry(entryId);
        } catch (DeleteEntryException e) {
            log.warn("error in delete");
        }
        return ResponseEntity.ok().build();
    }
}
