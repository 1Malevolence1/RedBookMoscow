package com.example.server_part_service.dtoshki;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    @NotNull
    private long id;
    private String name;
    private String originalFileName;
    private int size;
    private String contentType;
    private byte[] data;
}
