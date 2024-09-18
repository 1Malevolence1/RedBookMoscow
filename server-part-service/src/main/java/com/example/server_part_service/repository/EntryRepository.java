package com.example.server_part_service.repository;

import com.example.server_part_service.model.EntryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryRepository extends JpaRepository<EntryModel, Long> {

}
