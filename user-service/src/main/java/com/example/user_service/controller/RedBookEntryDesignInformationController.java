package com.example.user_service.controller;

import com.example.user_service.dto.ResponseDtoRedBookEntry;
import com.example.user_service.service.RedBookEntryDesignInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user/{entryId:\\d+}")
@RequiredArgsConstructor
public class RedBookEntryDesignInformationController {

    public final RedBookEntryDesignInformationService restClientService;

    @ModelAttribute("entry")
    public ResponseDtoRedBookEntry responseDtoRedBookEntry(@PathVariable(name = "entryId") Long id){
       return restClientService.findById(id).orElseThrow(() -> new NoSuchElementException("Entry not found with id %d"));
    }

    //TODO добавить навзание файла
    @GetMapping
    public String getRedBookEntryPage(@ModelAttribute("entry") ResponseDtoRedBookEntry responseDtoRedBookEntry,
                                      Model model){

        model.addAttribute("entry", responseDtoRedBookEntry);
        return "user_article";
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseStatusException handlerNoSuchElementException(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}

