package com.example.user_service.service;




import com.example.user_service.dto.ResponseDtoView;

import java.util.List;

public interface ViewService {

    List<ResponseDtoView> findAll();
}


