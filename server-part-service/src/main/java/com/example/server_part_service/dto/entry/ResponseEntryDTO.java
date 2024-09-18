package com.example.server_part_service.dto.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntryDTO {
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
