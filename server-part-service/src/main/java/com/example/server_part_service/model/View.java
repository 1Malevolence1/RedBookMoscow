package com.example.server_part_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "view")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @OneToMany(mappedBy = "view")
    private List<EntryModel> entryModels;

    @Override
    public String toString() {
        return "View{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
