package com.example.server_part_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "view")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "title", unique = true, nullable = false)
    private String title;

    public View(Long id) {
        this.id = id;
    }
}
