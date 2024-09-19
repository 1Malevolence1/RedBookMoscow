package com.example.user_service.dto;


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
