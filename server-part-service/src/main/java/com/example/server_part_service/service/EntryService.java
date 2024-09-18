package com.example.server_part_service.service;

import com.example.server_part_service.dto.entry.RequestEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTOFourFields;
import com.example.server_part_service.exception.EntityNotFoundException;
import com.example.server_part_service.model.EntryModel;
import com.example.server_part_service.model.ImageModel;
import com.example.server_part_service.model.View;
import com.example.server_part_service.repository.EntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public EntryModel getModelById(long entryId) {
        return entryRepository.findById(entryId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Image with id " + entryId + " not found"));
    }

    public void deleteAll(){
         entryRepository.deleteAll();
    }

    public List<ResponseEntryDTO> findAll() {
        List<EntryModel> entryModels = entryRepository.findAll();
        List<ResponseEntryDTO> responseEntryDTOS = entryModels
                .stream()
                .map(this::converterModelToResponseDto) // Уберите круглые скобки
                .toList(); // Преобразуем поток в список

        return responseEntryDTOS; // Не забудьте вернуть список
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
                getImageDataInBase64(model.getImageModel()),
                model.getView().getTitle()
        );
    }

    public EntryModel converterDtoToModel(RequestEntryDTO dto) {
        return new EntryModel(
                null,
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
                ImageService.convertDTOToImageModel(dto.getImage()),
                new View(null, dto.getView())
        );
    }

    public static String getImageDataInBase64(ImageModel image) {
        log.info("{}", image);// Конвертация массива байтов в строку Base64
        return "data:" + image.getContentType() + ";base64," + java.util.Base64.getEncoder().encodeToString(image.getData());}


    public List<ResponseEntryDTOFourFields> findAllPreview() {

        List<Object[]> fourFields1 = entryRepository.findFourFields();
        return null;
    }
}
