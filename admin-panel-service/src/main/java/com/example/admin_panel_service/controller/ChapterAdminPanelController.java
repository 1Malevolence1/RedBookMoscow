package com.example.admin_panel_service.controller;


import com.example.admin_panel_service.dto.mainpage.ResponseMainPageDtoEntry;
import com.example.admin_panel_service.dto.view.ResponseDtoView;
import com.example.admin_panel_service.service.ChapterAdminPanelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/admin/main")
@RequiredArgsConstructor
@Slf4j
public class ChapterAdminPanelController {


    private final ChapterAdminPanelService redBookEntryRetrievalService;


    //@TODO подмуть как над dto чтобы получать одним запрос данные для фильтра и всех redBookEntry

    @GetMapping()
    public String getMainPage(Model model){
        List<ResponseMainPageDtoEntry> responseRedBookEntryList = redBookEntryRetrievalService.findAll();
        List<ResponseDtoView> responseDtoViews = redBookEntryRetrievalService.getAllView();
        log.info("{}", responseRedBookEntryList);
        log.info("{}", responseDtoViews);
        model.addAttribute("list", responseRedBookEntryList);
        model.addAttribute("views", responseDtoViews);
        return "admin_index";
    }


}
