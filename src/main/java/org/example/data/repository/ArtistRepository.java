package org.example.data.repository;

import org.example.data.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findByUsername(String artistName);
}
