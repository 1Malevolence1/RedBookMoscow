package com.example.server_part_service.service;

import com.example.server_part_service.dto.ImageDTO;
import com.example.server_part_service.exception.EntityNotFoundException;
import com.example.server_part_service.model.EntryModel;
import com.example.server_part_service.model.ImageModel;
import com.example.server_part_service.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
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

    public ImageModel findById(long imageId) {
        return repository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Image with id " + imageId + " not found"));
    }


    public ImageDTO getImageDTO(long imageId) {
        return getDTO(findById(imageId));
    }


    public static ImageModel convertDTOToImageModel(ImageDTO imageDTO) {
        if (imageDTO == null) {
            return null;
        }

        ImageModel imageModel = new ImageModel();
        imageModel.setId(imageDTO.getId());
        imageModel.setName(imageDTO.getName());
        imageModel.setOriginalFileName(imageDTO.getOriginalFileName());
        imageModel.setSize(imageDTO.getSize());
        imageModel.setContentType(imageDTO.getContentType());
        imageModel.setData(imageDTO.getData());

        return imageModel;
    }

    public static ImageDTO convertModelToImageDTO(ImageModel imageModel) {
        if (imageModel == null) {
            return null;
        }

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(imageModel.getId());
        imageDTO.setName(imageModel.getName());
        imageDTO.setOriginalFileName(imageModel.getOriginalFileName());
        imageDTO.setSize(imageModel.getSize());
        imageDTO.setContentType(imageModel.getContentType());
        imageDTO.setData(imageModel.getData());

        return imageDTO;
    }


    public List<ImageModel> findImagesByEntry(EntryModel model) {
      //  return repository.findAllByEntryModel(model);
        return null;
    }
}
