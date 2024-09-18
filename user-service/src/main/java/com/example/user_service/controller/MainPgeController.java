package com.example.user_service.controller;


import com.example.user_service.dto.ResponseDtoRedBookEntry;
import com.example.user_service.service.MainPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/user/main")
@RequiredArgsConstructor
@Slf4j
public class MainPgeController {

    private final MainPageService mainPageService;

    //TODO добавить название файла

    @GetMapping()
    public String getMainPage(Model model){
        List<ResponseDtoRedBookEntry>  list = mainPageService.findAll();
        model.addAttribute("entry", list);
        return "...";
    }

}
