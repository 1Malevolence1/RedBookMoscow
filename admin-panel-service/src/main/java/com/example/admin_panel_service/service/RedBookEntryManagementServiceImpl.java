package com.example.admin_panel_service.service;


import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;
import com.example.config.uri.PathUriController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RedBookEntryManagementServiceImpl implements
         RedBookEntryManagementService{


    private final RestClient restClientDataBase;


    @Override
    public void deleteById(Long id) {
        restClientDataBase.delete().uri(PathUriController.DEL_RED_BOOL_ENTRY_DATA_BASE.formatted(id)).retrieve();
    }

    @Override
    public Optional<ResponseDtoRedBookEntry> findById(Long id) {
        try {
            return Optional.ofNullable(restClientDataBase.get().uri(PathUriController.GET_RED_BOOL_ENTRY_DATA_BASE.formatted(id)).retrieve().body(ResponseDtoRedBookEntry.class));
        } catch (HttpClientErrorException.NotFound errorException){
            return Optional.empty();
        }
    }

}
