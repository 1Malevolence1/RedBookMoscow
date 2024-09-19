package com.example.admin_panel_service.service.view;

import com.example.admin_panel_service.exeption.Validation;
import com.example.admin_panel_service.config.uri.PathUriController;
import com.example.admin_panel_service.dto.view.RequestDtoView;
import com.example.admin_panel_service.dto.view.ResponseDtoView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
        log.info("{}", PathUriController.DEL_VIEW_DATA_BASE.formatted(id));
        restClient.delete().uri(PathUriController.DEL_VIEW_DATA_BASE.formatted(id)).retrieve();
    }

    @SneakyThrows
    @Override
    public void save(RequestDtoView dtoView) {
        try {
            restClient.post().uri(PathUriController.POST_VIEW_DATA_BASE).contentType(MediaType.APPLICATION_JSON)
                    .body(dtoView).retrieve();
        }catch (HttpClientErrorException.BadRequest exception){
            throw new Validation("Данный вид уже занесён в базу данных");
        }
    }
}
