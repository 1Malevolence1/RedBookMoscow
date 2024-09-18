package com.example.server_part_service.controller;


import com.example.server_part_service.dto.view.RequestDtoView;
import com.example.server_part_service.dto.view.ResponseDtoView;
import com.example.server_part_service.exception.Validation;
import com.example.server_part_service.service.view.ViewService;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/server-part/view")
@RequiredArgsConstructor
public class ViewManagerRestController {

    private final ViewService viewService;

    @GetMapping()
    public ResponseEntity<List<ResponseDtoView>> getAllView() {
        return ResponseEntity.ok().body(viewService.finaAll());
    }


    @PostMapping()
    public ResponseEntity<?> saveNewViewInBaseDate(@RequestBody RequestDtoView dto) {
            viewService.save(new RequestDtoView(null, dto.title()));
            return ResponseEntity.ok().build();
        }


    @DeleteMapping("/delete/{viewId:\\d+}")
    public ResponseEntity<Validation> deleteView(@PathVariable(name = "view") Long id) {
        viewService.delete(id);
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Validation> handlerNoSuchElementException(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Validation(exception.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Validation> handleBadRequest(BadRequestException exception) {

        Validation validationResponse = new Validation("Данный вид уже занесён в базу");
        return ResponseEntity.badRequest().body(validationResponse);
    }
}


