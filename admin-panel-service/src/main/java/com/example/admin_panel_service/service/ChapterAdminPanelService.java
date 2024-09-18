package com.example.admin_panel_service.service;

import com.example.admin_panel_service.dto.view.ResponseDtoView;
import com.example.admin_panel_service.service.function.RedBookEntryRetrievalService;

import java.util.List;

public interface ChapterAdminPanelService extends RedBookEntryRetrievalService {

    List<ResponseDtoView> getAllView();
}
