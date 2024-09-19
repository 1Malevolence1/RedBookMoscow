package com.example.admin_panel_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDtoRedBookEntry {
    private Long id;
    private String name;
    private String latinName;
    private String division;
    private String family;

    private String status;
    private String distribution;
    private String inHabitat;
    private String habitatFeatures;

    private String mitigatingFactors;
    private String protectionMeasuresTaken;
    private String changesInStatusOfSpecies;
    private String neededConservationActions;

    private String sourcesOfInformation;
    private String authors;

    private List<String> data;
    private String view;//data in base64

}
