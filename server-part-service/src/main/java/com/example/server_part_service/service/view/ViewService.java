package com.example.server_part_service.service.view;


import com.example.server_part_service.dto.view.RequestDtoView;
import com.example.server_part_service.dto.view.ResponseDtoView;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ViewService {
    
    List<ResponseDtoView> finaAll();

    void save(RequestDtoView dto);

    void delete(Long id);
    
}
