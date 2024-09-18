package com.example.admin_panel_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDtoImage {
    @NotNull


    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private byte[] data;
}
