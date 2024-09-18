package com.example.server_part_service.repository.view;

import com.example.server_part_service.model.View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ViewRepository extends JpaRepository<View, Long> {
    boolean existsByTitle(String title);

    Optional<View> findByTitle(String title);
}
