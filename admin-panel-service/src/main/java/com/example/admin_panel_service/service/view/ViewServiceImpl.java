package com.example.admin_panel_service.service.view;

import com.example.admin_panel_service.config.uri.PathUriController;
import com.example.admin_panel_service.dto.view.ResponseDtoView;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {
    private final RestClient restClient;

   private final ParameterizedTypeReference<List<ResponseDtoView>>  LIST_VIEW = new ParameterizedTypeReference<List<ResponseDtoView>>() {
   };


    @Override
    public List<ResponseDtoView> findAll() {
        return restClient.get().uri(PathUriController.GET_LIST_VIEW_DATA_BASE).retrieve().body(LIST_VIEW);
    }


    @Override
    public void delete(Long id){
        restClient.delete().uri(PathUriController.DEL_VIEW_DATA_BASE).retrieve();
    }
}
