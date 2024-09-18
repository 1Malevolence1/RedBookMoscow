package com.example.admin_panel_service.controller;


import com.example.admin_panel_service.dto.RequestDtoImage;
import com.example.admin_panel_service.dto.RequestDtoRedBookEntry;
import com.example.admin_panel_service.dto.RequestDtoUpdateEntry;
import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;
import com.example.admin_panel_service.dto.view.ResponseDtoView;
import com.example.admin_panel_service.service.ConvertImage;
import com.example.admin_panel_service.service.RedBookEntryManagementService;
import com.example.admin_panel_service.service.UpdateRedBookEntryService;
import com.example.admin_panel_service.service.view.ViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api/admin/entry/update/{entryId:\\d+}")
@RequiredArgsConstructor
@Slf4j
public class UpdateRedBookEntryController {

    private final RedBookEntryManagementService redBookEntryDeleteAndFindService;
    private final ViewService viewService;
    private final UpdateRedBookEntryService updateRedBookEntryService;

    @ModelAttribute("entry")
    public ResponseDtoRedBookEntry responseRedBookEntry(@PathVariable(name = "entryId") Long id){
        return redBookEntryDeleteAndFindService
                .findById(id).orElseThrow(() -> new NoSuchElementException("Entry not found with id %d".formatted(id)));
    }


    @GetMapping()
    public String getPageCreateRedBookEntry(Model model, @ModelAttribute("entry") ResponseDtoRedBookEntry responseDtoRedBookEntry){
        List<ResponseDtoView> responseDtoViewList = viewService.findAll();
        log.info("Список {}", responseDtoViewList);
        model.addAttribute("payload", responseDtoRedBookEntry);
        model.addAttribute("views", responseDtoViewList);
        return "admin_update";
    }

    @PostMapping()
    public String update(@RequestParam("file") MultipartFile image,  @PathVariable(name = "entryId") Long id, RequestDtoUpdateEntry requestDtoRedBookEntry
    ) throws IOException {

        {
            requestDtoRedBookEntry.setId(id);
            log.info("{}", requestDtoRedBookEntry);
            RequestDtoImage requestDtoImage = ConvertImage.toImageEntity(image);
            requestDtoRedBookEntry.setImage(requestDtoImage);
            log.info("{}", requestDtoImage);
            ResponseDtoRedBookEntry responseDtoRedBookEntry = updateRedBookEntryService.update(requestDtoRedBookEntry);
            return "redirect:/api/admin/entry/%d".formatted(responseDtoRedBookEntry.getId()); // Перенаправление на главную страницу после успешного создания записи
        }
    }


}
