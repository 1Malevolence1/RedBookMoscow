package com.example.admin_panel_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDtoRedBookEntry {
    private String name;
    private String latinName;
    private String division;
    private String family;
    private String status;
    private String distribution;
    private String inhabitat;
    private String mitigatingFactor;
    private String ProtectionMeasuresTaken;
    private String ChangesINinTheStatusOfTheSpecies;
    private String neededConservationcAtions;
    private RequestDtoImage image;
    private String view;

}
