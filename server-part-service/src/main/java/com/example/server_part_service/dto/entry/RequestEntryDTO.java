package com.example.server_part_service.dto.entry;

import com.example.server_part_service.dto.ImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEntryDTO {
    private Long id;
    private String name;
    private String latinName;
    private String division;
    private String Family;

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

    private ImageDTO image;
    private String view;// image dto in json format

}
