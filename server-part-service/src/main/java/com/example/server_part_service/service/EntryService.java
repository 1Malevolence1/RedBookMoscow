package com.example.server_part_service.service;

import com.example.server_part_service.dto.entry.RequestEntryDTO;
import com.example.server_part_service.dto.entry.ResponseEntryDTO;
import com.example.server_part_service.exception.EntityNotFoundException;
import com.example.server_part_service.model.EntryModel;
import com.example.server_part_service.model.ImageModel;
import com.example.server_part_service.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public EntryModel getModelById(long entryId) {
        return entryRepository.findById(entryId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Image with id " + entryId + " not found"));
    }

//    public RequestEntryDTO getDtoById(long id) {
//        return converterModelToDto(getModelById(id));
//    }

    public EntryModel saveModel(EntryModel entryModel) {
        return entryRepository.save(entryModel);
    }

    public EntryModel updateModel(EntryModel entryModel) {
        return entryRepository.save(entryModel);
    }

    public void deleteEntry(EntryModel model) {

    }

    public void deleteEntry(Long entryId) {

    }

    public ResponseEntryDTO converterModelToResponseDto(EntryModel model) {
        return new ResponseEntryDTO(
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
                model.getAuthors(), null

        );
    }

    public EntryModel converterDtoToModel(RequestEntryDTO dto) {
        return new EntryModel(
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
                dto.getAuthors(), null
        );
    }

    public static String getImageDataInBase64(ImageModel image) {    // Конвертация массива байтов в строку Base64
        return "data:" + image.getContentType() + ";base64," + java.util.Base64.getEncoder().encodeToString(image.getData());}


}
