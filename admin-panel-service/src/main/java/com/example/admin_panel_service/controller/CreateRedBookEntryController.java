package com.example.admin_panel_service.controller;


import com.example.admin_panel_service.dto.RequestDtoImage;
import com.example.admin_panel_service.dto.RequestDtoRedBookEntry;
import com.example.admin_panel_service.dto.view.ResponseDtoView;
import com.example.admin_panel_service.service.ConvertImage;
import com.example.admin_panel_service.service.function.RedBookEntryCreationService;
import com.example.admin_panel_service.service.view.ViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/api/admin/entry/create")
@RequiredArgsConstructor
@Slf4j
public class CreateRedBookEntryController {
    public final RedBookEntryCreationService redBookEntryService;
    private final ViewService viewService;

    //@TODO обновить под под нужный
    @GetMapping()
    public String getPageCreateRedBookEntry(Model model){
        List<ResponseDtoView> responseDtoViewList = viewService.findAll();
        log.info("Список {}", responseDtoViewList);
        model.addAttribute("views", responseDtoViewList);
        return "admin_add";
    }

    @PostMapping()
    public String createNewRedBookEntry(@RequestParam("file") MultipartFile image, RequestDtoRedBookEntry requestDtoRedBookEntry
                                       ) throws IOException {

      {
            log.info("{}", requestDtoRedBookEntry);
            RequestDtoImage requestDtoImage = ConvertImage.toImageEntity(image);
          requestDtoRedBookEntry.setData(requestDtoImage);
            log.info("{}", requestDtoImage);
            redBookEntryService.save(requestDtoRedBookEntry);
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
