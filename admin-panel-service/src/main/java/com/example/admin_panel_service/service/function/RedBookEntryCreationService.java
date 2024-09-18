package com.example.admin_panel_service.service.function;

import com.example.admin_panel_service.dto.RequestDtoRedBookEntry;
import com.example.admin_panel_service.dto.mainpage.RequestMainPageDtoEntry;
import com.example.admin_panel_service.dto.mainpage.ResponseMainPageDtoEntry;

public interface RedBookEntryCreationService {
    void save(RequestDtoRedBookEntry requesterCreateRedBookEntry);
}
