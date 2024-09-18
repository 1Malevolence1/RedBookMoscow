package com.example.server_part_service.service;

import com.example.server_part_service.dtoshki.ImageDTO;
import com.example.server_part_service.exception.EntityNotFoundException;
import com.example.server_part_service.model.ImageModel;
import com.example.server_part_service.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageService.class);
    @Autowired
    private ImageRepository repository;

    public ImageModel saveImage(ImageModel imageModel) {
        return repository.save(imageModel);
    }

    public ImageDTO getDTO(ImageModel imageModel) {
        return new ImageDTO(
                imageModel.getId(),
                imageModel.getName(),
                imageModel.getOriginalFileName(),
                imageModel.getSize(),
                imageModel.getContentType(),
                imageModel.getData()
        );
    }

    public ImageModel getImage(long imageId) {
        return repository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Image with id " + imageId + " not found"));
    }


    public ImageDTO getImageDTO(long imageId) {
        return getDTO(getImage(imageId));
    }
}
