package com.example.server_part_service.repository;

import com.example.server_part_service.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    //optional
}
