package com.example.admin_panel_service.service;


import com.example.admin_panel_service.dto.RequestDtoImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public final class ConvertImage {


    public static RequestDtoImage toImageEntity(MultipartFile file) throws IOException, IOException {
        RequestDtoImage image = new RequestDtoImage();

        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());

        return image;
    }



}
