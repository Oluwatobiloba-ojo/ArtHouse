package org.example.data.repository;

import org.example.data.model.Art;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtRepository extends JpaRepository<Art, Long> {
    Optional<Art> findById(Long artId);

}
