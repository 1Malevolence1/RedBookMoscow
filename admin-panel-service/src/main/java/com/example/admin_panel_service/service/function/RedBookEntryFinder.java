package com.example.admin_panel_service.service.function;

import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;

import java.util.Optional;

public interface RedBookEntryFinder {
    Optional<ResponseDtoRedBookEntry> findById(Long id);
}
