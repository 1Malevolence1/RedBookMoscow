package com.example.admin_panel_service.controller;


import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;
import com.example.admin_panel_service.service.RedBookEntryManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api/admin/entry/{entryId:\\d+}")
@RequiredArgsConstructor
@Slf4j
public class RedBookEntryManagementController{

    private final RedBookEntryManagementService redBookEntryDeleteAndFindService;

    @ModelAttribute("entry")
    public ResponseDtoRedBookEntry responseRedBookEntry(@PathVariable(name = "entryId") Long id){
        return redBookEntryDeleteAndFindService
                .findById(id).orElseThrow(() -> new NoSuchElementException("Entry not found with id %d".formatted(id)));
    }


    // @TODO - добавить названия html файла
    @GetMapping()
    public String getRedBookInfoPage(@ModelAttribute("entry") ResponseDtoRedBookEntry entry, Model model){
        model.addAttribute("entry", entry);
        return "...";
    }



    // @TODO - добавить названия html файла
    @DeleteMapping("delete")
    public String deleteRedBook(@PathVariable(name = "entryId") Long id){
        redBookEntryDeleteAndFindService.deleteById(id);
        return "redirect:/...";
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseStatusException handlerNoSuchElementException(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
