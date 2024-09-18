package com.example.user_service.service.function;



import com.example.user_service.dto.ResponseDtoRedBookEntry;

import java.util.List;

public interface RedBookEntryRetrievalService {
    List<ResponseDtoRedBookEntry> findAll();
}
