package com.example.server_part_service.repository;

import com.example.server_part_service.model.EntryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryRepository extends JpaRepository<EntryModel, Long> {

    @Query(value = "SELECT e.id, e.name, e.latin_name, im FROM entry_model e JOIN image_model im ON e.image_id = im.id", nativeQuery = true)
    List<Object[]> findFourFields();
}
