package com.example.server_part_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "entry_model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @OneToMany(mappedBy = "entry_model", cascade = CascadeType.ALL)
//    @JoinColumn(name = "image_id")
    private List<ImageModel> data;

    @ManyToOne()
    @JoinColumn(name = "view_id")
    private View view;

}
