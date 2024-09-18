package com.example.user_service.service;

import com.example.user_service.config.uri.PathUriController;
import com.example.user_service.dto.ResponseDtoRedBookEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedBookEntryDesignInformationServiceImpl implements RedBookEntryDesignInformationService {

    
    private final RestClient restClient;


    @Override
    public Optional<ResponseDtoRedBookEntry> findById(Long id) {
        try {
            return Optional.ofNullable(restClient.get()
                    .uri(PathUriController.GET__RED_BOK_ENTRY_DATA_BASE)
                    .retrieve()
                    .body(ResponseDtoRedBookEntry.class));
        } catch (HttpClientErrorException.NotFound exception) {
            return Optional.empty();
        }
    }
}