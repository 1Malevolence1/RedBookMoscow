package com.example.server_part_service.dto.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntryDTOFourFields {
    private Long id;
    private String name;
    private String latinName;
    private long data;
}
