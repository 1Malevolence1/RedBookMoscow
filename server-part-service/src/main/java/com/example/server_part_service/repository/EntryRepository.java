package com.example.server_part_service.repository;

import com.example.server_part_service.model.EntryModel;
import com.example.server_part_service.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryRepository extends JpaRepository<EntryModel, Long> {

  //  @Query(value = "SELECT e.id, e.name, e.latin_name, e.image_id, e.view_id FROM entry_model e", nativeQuery = true)
   // List<Object[]> findFourFields();

}
