package com.example.server_part_service.service;

import com.example.server_part_service.dtoshki.EntryDTO;
import com.example.server_part_service.exception.EntityNotFoundException;
import com.example.server_part_service.model.EntryModel;
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

}
