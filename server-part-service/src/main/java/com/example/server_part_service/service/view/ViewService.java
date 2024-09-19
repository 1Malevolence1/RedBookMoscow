package com.example.server_part_service.service.view;


import com.example.server_part_service.dto.view.RequestDtoView;
import com.example.server_part_service.dto.view.ResponseDtoView;
import com.example.server_part_service.model.View;
import org.apache.coyote.BadRequestException;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface ViewService {
    
    List<ResponseDtoView> finaAll();

    void save(RequestDtoView dto);

    View save(View view);

    void delete(Long id);

    View saveIfNotExist(View view);

    Optional<View> findById(long l);
}
