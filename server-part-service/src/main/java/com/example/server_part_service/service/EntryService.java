package com.example.server_part_service.service;

import com.example.server_part_service.dto.entry.RequestEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTOFourFields;
import com.example.server_part_service.exception.EntityNotFoundException;
import com.example.server_part_service.model.EntryModel;
import com.example.server_part_service.model.ImageModel;
import com.example.server_part_service.model.View;
import com.example.server_part_service.repository.EntryRepository;
import com.example.server_part_service.service.view.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private ViewService viewService;
    @Autowired
    private ImageService imageService;

    public EntryModel getModelById(long entryId) {
        return entryRepository.findById(entryId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Image with id " + entryId + " not found"));
    }

    public void deleteAll() {
        entryRepository.deleteAll();
    }

    public List<ResponseEntryDTO> findAll() {
        try {
            List<EntryModel> entryModels = entryRepository.findAll();
            return entryModels
                    .stream()
                    .map(this::converterModelToResponseDto)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntryDTO getDtoById(long id) {
        return converterModelToResponseDto(getModelById(id));
    }

    public EntryModel saveModel(EntryModel entryModel) {
        return entryRepository.save(entryModel);
    }

    public EntryModel updateModel(EntryModel entryModel) {
        return entryRepository.save(entryModel);
    }

    public void deleteEntry(EntryModel model) {
        entryRepository.delete(model);
    }

    public void deleteEntry(Long entryId) {
        entryRepository.deleteById(entryId);
    }

    public ResponseEntryDTO converterModelToResponseDto(EntryModel model) {
        return new ResponseEntryDTO(
                model.getId(),
                model.getName(),
                model.getLatinName(),
                model.getDivision(),
                model.getFamily(),
                model.getStatus(),
                model.getDistribution(),
                model.getInHabitat(),
                model.getHabitatFeatures(),
                model.getMitigatingFactors(),
                model.getProtectionMeasuresTaken(),
                model.getChangesInStatusOfSpecies(),
                model.getNeededConservationActions(),
                model.getSourcesOfInformation(),
                model.getAuthors(),
                model.getData().stream()
                        .map(this::getImageDataInBase64)
                        .collect(Collectors.toList()),
                model.getView().getTitle()
        );
    }

    public EntryModel converterDtoToModel(RequestEntryDTO dto) {
        return new EntryModel(
                dto.getId(),
                dto.getName(),
                dto.getLatinName(),
                dto.getDivision(),
                dto.getFamily(),
                dto.getStatus(),
                dto.getDistribution(),
                dto.getInHabitat(),
                dto.getHabitatFeatures(),
                dto.getMitigatingFactors(),
                dto.getProtectionMeasuresTaken(),
                dto.getChangesInStatusOfSpecies(),
                dto.getNeededConservationActions(),
                dto.getSourcesOfInformation(),
                dto.getAuthors(),


                dto.getData().stream()
                        .map(ImageService::convertDTOToImageModel)
                        .collect(Collectors.toList()),
                viewService.save(new View(0L, dto.getView()))
        );
    }

    public String getImageDataInBase64(ImageModel image) {
        log.info("{}", image);// Конвертация массива байтов в строку Base64
        return "data:" + image.getContentType() + ";base64," + java.util.Base64.getEncoder().encodeToString(image.getData());
    }


    public List<ResponseEntryDTOFourFields> findAllPreview() {
        List<Object[]> fourFields1 = entryRepository.findFourFields();
        return fourFields1.stream()
                .map(row -> {
                    ResponseEntryDTOFourFields dto = new ResponseEntryDTOFourFields();
                    dto.setId((Long) row[0]);
                    dto.setName((String) row[1]);
                    dto.setLatinName((String) row[2]);
                    dto.setData(getImageDataInBase64(imageService.findById((Long) row[3]))); // Преобразование data, предполагается, что это long
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
