package com.example.server_part_service.convert.view;

import com.example.server_part_service.convert.ConvertDtoReturnEntity;
import com.example.server_part_service.convert.ConvertEntityReturnDto;

public interface ConvertView<RequestDto, ResponseDto, Entity> extends ConvertDtoReturnEntity<RequestDto, Entity>
        , ConvertEntityReturnDto<ResponseDto, Entity> {
}
