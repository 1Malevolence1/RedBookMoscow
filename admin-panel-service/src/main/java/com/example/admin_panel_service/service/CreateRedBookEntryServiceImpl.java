package com.example.admin_panel_service.service;

import com.example.admin_panel_service.dto.RequestDtoRedBookEntry;
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
    public void save(RequestDtoRedBookEntry entry) {
            restClientDataBase.post().uri(com.example.config.uri.PathUriController.POST_RED_BOOL_ENTRY_DATA_BASE).contentType(MediaType.APPLICATION_JSON).body(entry).retrieve();
    }
}
