package com.example.server_part_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "book", name = "animal_obj")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryModel {
    @NotNull
    private String name;
    @NotNull
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageModel imageModel;

}
