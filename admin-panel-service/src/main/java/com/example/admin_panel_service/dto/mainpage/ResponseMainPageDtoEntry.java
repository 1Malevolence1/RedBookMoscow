package com.example.admin_panel_service.dto.mainpage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMainPageDtoEntry {
        private Long id;
        private String name;
        private String latinName;
        private String data;
        private String view;
}
