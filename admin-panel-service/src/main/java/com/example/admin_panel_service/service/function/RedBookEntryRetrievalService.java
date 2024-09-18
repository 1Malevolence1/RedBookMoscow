package com.example.admin_panel_service.service.function;

import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;
import com.example.admin_panel_service.dto.mainpage.ResponseMainPageDtoEntry;

import java.util.List;

public interface RedBookEntryRetrievalService {
    List<ResponseMainPageDtoEntry> findAll();
}
