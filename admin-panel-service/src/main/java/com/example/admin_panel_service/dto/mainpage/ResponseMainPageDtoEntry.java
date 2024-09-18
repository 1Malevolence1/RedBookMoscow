package com.example.admin_panel_service.dto.mainpage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMainPageDtoEntry {
    private String name;
    private String status;
    private String image;
}
