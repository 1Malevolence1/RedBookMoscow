package com.example.admin_panel_service.controller;


import com.example.admin_panel_service.dto.RequestDtoRedBookEntry;
import com.example.admin_panel_service.service.function.RedBookEntryCreationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@TODO обновить под под нужный
@Controller
@RequestMapping("/api/admin/entry/create")
@RequiredArgsConstructor
@Slf4j
public class CreateRedBookEntryController {
    public final RedBookEntryCreationService redBookEntryService;

    //@TODO обновить под под нужный
    @GetMapping()
    public String getPageCreateRedBookEntry(){
        return "...";
    }

    //@TODO обновить под под нужный
    @PostMapping()
    public String createNewRedBookEntry(@Valid @ModelAttribute RequestDtoRedBookEntry createRedBookEntry, BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            redBookEntryService.save(createRedBookEntry);
            return "...";
        }
    }


    //@TODO обновить под под нужный
    @ExceptionHandler(BindException.class)
    public String handlerBindException(BindException exception, Model model){
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        model.addAttribute("errors", errors);
        log.info("{}", errors);
        return "redirect://...";
    }
}
