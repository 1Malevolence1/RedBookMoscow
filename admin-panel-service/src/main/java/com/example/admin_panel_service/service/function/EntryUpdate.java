package com.example.admin_panel_service.service.function;

import com.example.admin_panel_service.dto.RequestDtoImage;
import com.example.admin_panel_service.dto.RequestDtoRedBookEntry;
import com.example.admin_panel_service.dto.RequestDtoUpdateEntry;
import com.example.admin_panel_service.dto.ResponseDtoRedBookEntry;

public interface EntryUpdate {
    ResponseDtoRedBookEntry update(RequestDtoUpdateEntry requestDtoImage);
}
