package com.example.user_service.service.function;



import com.example.user_service.dto.ResponseDtoRedBookEntry;
import com.example.user_service.dto.ResponseMainPageDtoEntry;

import java.util.List;

public interface RedBookEntryRetrievalService {
    List<ResponseMainPageDtoEntry> findAll();
}
