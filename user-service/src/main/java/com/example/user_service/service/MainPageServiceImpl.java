package com.example.user_service.service;

import com.example.user_service.config.uri.PathUriController;
import com.example.user_service.dto.ResponseDtoRedBookEntry;
import com.example.user_service.dto.ResponseDtoView;
import com.example.user_service.dto.ResponseMainPageDtoEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {


    private final RestClient restClient;
    private final ViewService viewService;

    private final ParameterizedTypeReference<List<ResponseMainPageDtoEntry>> PARAMETETERIZED_LIST_ENTRY =
            new ParameterizedTypeReference<List<ResponseMainPageDtoEntry>>() {
            };

    private final ParameterizedTypeReference<List<ResponseDtoView>> PARAMETETERIZED_LIST_VIEW =
            new ParameterizedTypeReference<List<ResponseDtoView>>() {
            };



    @Override
    public List<ResponseMainPageDtoEntry> findAll() {
        return restClient.get().uri(PathUriController.GET_LIST_RED_BOK_ENTRY_DATA_BASE).retrieve().body(PARAMETETERIZED_LIST_ENTRY);
    }



    @Override
    public List<ResponseDtoView> finaAllView() {
        return restClient.get().uri(PathUriController.GET_LIST_RED_BOK_ENTRY_DATA_BASE).retrieve().body(PARAMETETERIZED_LIST_VIEW);
    }
}
