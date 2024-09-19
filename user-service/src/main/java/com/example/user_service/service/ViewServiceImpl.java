package com.example.user_service.service;


import com.example.user_service.config.uri.PathUriController;
import com.example.user_service.dto.ResponseDtoView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ViewServiceImpl implements ViewService {
    private final RestClient restClient;

   private final ParameterizedTypeReference<List<ResponseDtoView>>  LIST_VIEW = new ParameterizedTypeReference<List<ResponseDtoView>>() {
   };


    @Override
    public List<ResponseDtoView> findAll() {
        return restClient.get().uri(PathUriController.GET_LIST_VIEW_DATA_BASE).retrieve().body(LIST_VIEW);
    }
}
