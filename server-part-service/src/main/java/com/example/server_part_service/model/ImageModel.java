package com.example.server_part_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "image_models")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String originalFileName;
    private int size;
    @ManyToOne
    @JoinColumn(name = "entryModel_id")
    private EntryModel entryModel;

    private String contentType;
    private byte[] data;

    @Override
    public String toString() {
        return "ImageModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalFileName='" + originalFileName + '\'' +
                ", size=" + size +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
