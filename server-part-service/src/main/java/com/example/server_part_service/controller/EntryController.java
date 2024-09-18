package com.example.server_part_service.controller;

import com.example.server_part_service.dto.entry.RequestEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTOFourFields;
import com.example.server_part_service.model.EntryModel;
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
    public ResponseEntity<List<ResponseEntryDTOFourFields>> getListEntryForMainPage(){
        return ResponseEntity.ok().body(service.findAllPreview());
    }

    @GetMapping("/all-with-all-parametrs")
    public ResponseEntity<List<ResponseEntryDTO>> getListEntryAllData(){
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
        } catch (Exception e) {
            log.info("\n--------------\nerror in post entry!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }
    @PutMapping
    public ResponseEntity<ResponseEntryDTO> put( RequestEntryDTO dto) {
        ResponseEntryDTO responseEntryDTO = service.converterModelToResponseDto(service.updateModel(service.converterDtoToModel(dto)));
        return new ResponseEntity<>(responseEntryDTO, HttpStatus.valueOf(200));
    }
    @DeleteMapping()
    public ResponseEntity<Void> delete() {
       service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
