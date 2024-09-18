package com.example.admin_panel_service.dto.mainpage;


import com.example.admin_panel_service.dto.RequestDtoImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestMainPageDtoEntry {
    private String name;
    private String latinNam;
    private RequestDtoImage image;
}
