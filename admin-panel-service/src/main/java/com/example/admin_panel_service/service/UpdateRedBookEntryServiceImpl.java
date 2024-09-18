package com.example.admin_panel_service.service;

import com.example.admin_panel_service.config.uri.PathUriController;
import com.example.admin_panel_service.dto.RequestDtoRedBookEntry;
import com.example.admin_panel_service.dto.RequestDtoUpdateEntry;
import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class UpdateRedBookEntryServiceImpl implements UpdateRedBookEntryService {

    private final RestClient restClientDataBase;

    @Override
    public ResponseDtoRedBookEntry update(RequestDtoUpdateEntry requestDtoRedBookEntry) {
       return restClientDataBase.put().uri(PathUriController.PUT_RED_BOOL_ENTRY_DATA_BASE.formatted(requestDtoRedBookEntry.getId())).contentType(MediaType.APPLICATION_JSON).body(requestDtoRedBookEntry)
                .retrieve().body(ResponseDtoRedBookEntry.class);
    }
}
