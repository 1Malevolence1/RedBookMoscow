package com.example.admin_panel_service.controller;


import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;
import com.example.admin_panel_service.service.ChapterAdminPanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/admin/main")
@RequiredArgsConstructor
public class ChapterAdminPanelController {


    private final ChapterAdminPanelService redBookEntryRetrievalService;


    //@TODO подмуть как над dto чтобы получать одним запрос данные для фильтра и всех redBookEntry

    @GetMapping()
    public String getMainPage(Model model){

        List<ResponseDtoRedBookEntry> responseRedBookEntryList = redBookEntryRetrievalService.findAll();

        model.addAttribute("list", responseRedBookEntryList);
        return "";
    }


}
