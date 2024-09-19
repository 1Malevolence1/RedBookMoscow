package com.example.server_part_service.convert.view;

import com.example.server_part_service.dto.view.RequestDtoView;
import com.example.server_part_service.dto.view.ResponseDtoView;
import com.example.server_part_service.model.View;
import org.springframework.stereotype.Component;


@Component
public class ConvertViewImpl implements ConvertView <RequestDtoView, ResponseDtoView, View> {
    @Override
    public View convertDtoInEntity(RequestDtoView requestDtoView) {
        return new View(0L, requestDtoView.title(),null);
         }

    @Override
    public ResponseDtoView convertEntityInDto(View view) {
        return new ResponseDtoView(view.getId(), view.getTitle());
    }
}
