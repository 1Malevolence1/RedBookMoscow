package com.example.admin_panel_service.service;

import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;
import com.example.admin_panel_service.config.uri.PathUriController;
import com.example.admin_panel_service.dto.mainpage.ResponseMainPageDtoEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ChapterAdminPanelServiceImpl implements ChapterAdminPanelService {

    private final RestClient restClientDataBase;

    private final ParameterizedTypeReference<List<ResponseMainPageDtoEntry>> PARAMETERIZE_RED_BOOK_ENTRY =
            new ParameterizedTypeReference<List<ResponseMainPageDtoEntry>>() {
            } ;



    @Override
    public List<ResponseMainPageDtoEntry> findAll() {
        return restClientDataBase.get().uri(PathUriController.GET_LIST_RED_BOK_ENTRY_DATA_BASE).retrieve().body(
                PARAMETERIZE_RED_BOOK_ENTRY);
    }
}
