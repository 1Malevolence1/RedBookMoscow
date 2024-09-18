package com.example.admin_panel_service.service.view;


import com.example.admin_panel_service.dto.view.RequestDtoView;
import com.example.admin_panel_service.dto.view.ResponseDtoView;

import java.util.List;

public interface ViewService {

    List<ResponseDtoView> findAll();

    void delete(Long id);

    void save(RequestDtoView dtoView);
}
