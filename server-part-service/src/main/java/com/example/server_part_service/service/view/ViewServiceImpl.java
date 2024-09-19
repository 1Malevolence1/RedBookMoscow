package com.example.server_part_service.service.view;

import com.example.server_part_service.convert.view.ConvertView;
import com.example.server_part_service.dto.view.RequestDtoView;
import com.example.server_part_service.dto.view.ResponseDtoView;
import com.example.server_part_service.exception.EntityNotFoundException;
import com.example.server_part_service.model.View;
import com.example.server_part_service.repository.view.ViewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class ViewServiceImpl implements ViewService {

    private final ViewRepository viewRepository;
    private final ConvertView<RequestDtoView, ResponseDtoView, View> convertView;

    @Override
    public List<ResponseDtoView> finaAll() {
        return viewRepository.findAll().stream().map(convertView::convertEntityInDto).toList();
    }

    @SneakyThrows
    @Override
    @Transactional
    public void save(RequestDtoView dto)   {
        try {
            View view = new View();
            view.setId(0L);
            view.setTitle(dto.title());
            viewRepository.save(view);
        } catch (DataIntegrityViolationException e) {

            if (e.getCause() instanceof PSQLException) {
                PSQLException sqlException = (PSQLException) e.getCause();
                if ("23505".equals(sqlException.getSQLState())) {
                    throw new BadRequestException("Запись с таким заголовком уже существует: " + dto.title());
                }
            }
            throw new BadRequestException("Ошибка при сохранении: " + e.getMessage());
        }
    }

    @Override
    public View save(View view) {
        return viewRepository.save(view);
    }

    @Override
    @Transactional
    public void delete(Long id) {
       try {
           viewRepository.deleteById(id);
       } catch (NoSuchElementException exception){
           throw new NoSuchElementException("Not find view with id %d");
       }
    }

    @Override
    public View saveIfNotExist(View view) {
        boolean ifExist = viewRepository.existsByTitle(view.getTitle());
        log.info("\nexist = {}",ifExist);
        if (ifExist) {
            return viewRepository.findByTitle(view.getTitle()).orElseThrow(() -> new EntityNotFoundException("exception in find view"));
        }
        return viewRepository.save(view);
    }
}
