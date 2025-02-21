package com.example.admin_panel_service.controller;


import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;
import com.example.admin_panel_service.dto.view.ResponseDtoView;
import com.example.admin_panel_service.service.RedBookEntryManagementService;
import com.example.admin_panel_service.service.view.ViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api/admin/entry/{entryId:\\d+}")
@RequiredArgsConstructor
@Slf4j
public class RedBookEntryManagementController{

    private final RedBookEntryManagementService redBookEntryDeleteAndFindService;


    @ModelAttribute("entry")
    public ResponseDtoRedBookEntry responseRedBookEntry(@PathVariable(name = "entryId") Long id){
        ResponseDtoRedBookEntry responseDtoRedBookEntry = redBookEntryDeleteAndFindService
                .findById(id).orElseThrow(() -> new NoSuchElementException("Entry not found with id %d".formatted(id)));

        log.info("000000000000000000{}", responseDtoRedBookEntry);
        log.info(".....{}", responseDtoRedBookEntry.getData().size());
        return responseDtoRedBookEntry;
    }




    // @TODO - добавить названия html файла
    @GetMapping()
    public String getRedBookInfoPage(@ModelAttribute("entry") ResponseDtoRedBookEntry entry, Model model){
        log.info("->>>>>>>>>>>>>>{}", entry);
        model.addAttribute("entry", entry);
        return "admin_article";
    }



    // @TODO - добавить названия html файла
    @PostMapping("delete")
    public String deleteRedBook(@PathVariable(name = "entryId") Long id){
        redBookEntryDeleteAndFindService.deleteById(id);
        return "redirect:/api/admin/main";
    }



    @ExceptionHandler(NoSuchElementException.class)
    public ResponseStatusException handlerNoSuchElementException(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
