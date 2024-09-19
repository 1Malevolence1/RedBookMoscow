package com.example.user_service.service;

import com.example.user_service.config.uri.PathUriController;
import com.example.user_service.dto.ResponseDtoRedBookEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {


    private final RestClient restClient;

    private final ParameterizedTypeReference<List<ResponseDtoRedBookEntry>> PARAMETETERIZED_LIST_ENTRY =
            new ParameterizedTypeReference<List<ResponseDtoRedBookEntry>>() {
            };


    @Override
    public List<ResponseDtoRedBookEntry> findAll() {
        return restClient.get().uri(PathUriController.GET_LIST_RED_BOK_ENTRY_DATA_BASE).retrieve().body(PARAMETETERIZED_LIST_ENTRY);
    }
}
