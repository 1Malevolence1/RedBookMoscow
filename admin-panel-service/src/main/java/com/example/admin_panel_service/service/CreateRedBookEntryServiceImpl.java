package com.example.admin_panel_service.service;

import com.example.admin_panel_service.config.uri.PathUriController;
import com.example.admin_panel_service.dto.RequestDtoRedBookEntry;
import com.example.admin_panel_service.dto.mainpage.RequestMainPageDtoEntry;
import com.example.admin_panel_service.dto.mainpage.ResponseMainPageDtoEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
@Slf4j
@RequiredArgsConstructor
public class CreateRedBookEntryServiceImpl implements
        CreateRedBookEntryService {

    private final RestClient restClientDataBase;

    @Override
    public void save(RequestMainPageDtoEntry entry) {
            restClientDataBase.post().uri(PathUriController.POST_RED_BOOL_ENTRY_DATA_BASE).contentType(MediaType.APPLICATION_JSON).body(entry).retrieve();
    }
}
