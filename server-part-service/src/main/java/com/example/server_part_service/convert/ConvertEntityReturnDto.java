package com.example.server_part_service.convert;


public interface ConvertEntityReturnDto<Dto, Entity> {

    Dto convertEntityInDto(Entity entity);
}
