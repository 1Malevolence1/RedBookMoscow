package com.example.admin_panel_service.service;

import com.example.admin_panel_service.service.function.RedBookEntryDeletionService;
import com.example.admin_panel_service.service.function.RedBookEntryFinder;

public interface RedBookEntryManagementService extends
        RedBookEntryFinder, RedBookEntryDeletionService {
}
