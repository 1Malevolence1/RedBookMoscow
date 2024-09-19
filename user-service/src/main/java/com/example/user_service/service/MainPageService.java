package com.example.user_service.service;

import com.example.user_service.dto.ResponseDtoView;
import com.example.user_service.service.function.RedBookEntryRetrievalService;

import java.util.List;

public interface MainPageService extends RedBookEntryRetrievalService {

    List<ResponseDtoView> finaAllView();
}
