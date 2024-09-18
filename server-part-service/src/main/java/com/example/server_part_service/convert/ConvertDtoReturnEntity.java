package com.example.server_part_service.convert;


public interface ConvertDtoReturnEntity<Dto, Entity> {
    Entity convertDtoInEntity(Dto dto);
}
