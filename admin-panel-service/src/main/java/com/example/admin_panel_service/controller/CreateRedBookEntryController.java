package com.example.admin_panel_service.controller;


import com.example.admin_panel_service.dto.RequestDtoImage;
import com.example.admin_panel_service.dto.RequestDtoRedBookEntry;
import com.example.admin_panel_service.dto.mainpage.RequestMainPageDtoEntry;
import com.example.admin_panel_service.dto.mainpage.ResponseMainPageDtoEntry;
import com.example.admin_panel_service.service.ConvertImage;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

        return "admin_add";
    }

    @PostMapping()
    public String createNewRedBookEntry(@RequestParam("file") MultipartFile image, RequestMainPageDtoEntry responseMainPageDtoEntry
                                        ) throws IOException {

      {
            // Конвертируем MultipartFile в RequestDtoImage
            RequestDtoImage requestDtoImage = ConvertImage.toImageEntity(image);
            responseMainPageDtoEntry.setImage(requestDtoImage);
            log.info("{}", requestDtoImage);
            redBookEntryService.save(responseMainPageDtoEntry);
            return "redirect:/api/admin/main"; // Перенаправление на главную страницу после успешного создания записи
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
