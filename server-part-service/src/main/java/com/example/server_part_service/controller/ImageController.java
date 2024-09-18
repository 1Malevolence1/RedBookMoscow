package com.example.server_part_service.controller;

import com.example.server_part_service.dtoshki.ImageDTO;
import com.example.server_part_service.exception.EntityNotFoundException;
import com.example.server_part_service.model.Image;
import com.example.server_part_service.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);
    @Autowired
    private ImageService service;

    @GetMapping(value = "/get")
    public ResponseEntity<ImageDTO> getImage(long imageId) {
        try {
            log.info("\nimageId={}\n---------------------", imageId);
            return new ResponseEntity<>(service.getImageDTO(imageId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(515));
        }
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ImageDTO create(@RequestBody ImageDTO imageDTO) {
        log.info("{}",imageDTO.getData());
        return imageDTO;
    }
}
