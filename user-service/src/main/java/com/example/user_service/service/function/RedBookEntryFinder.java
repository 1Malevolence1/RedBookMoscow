package com.example.user_service.service.function;



import com.example.user_service.dto.ResponseDtoRedBookEntry;

import java.util.Optional;

public interface RedBookEntryFinder {
    Optional<ResponseDtoRedBookEntry> findById(Long id);
}
