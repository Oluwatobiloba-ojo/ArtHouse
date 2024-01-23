package org.example.data.repository;

import org.example.data.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findByEmail(String email);



    Artist findByUsername(String artistName);
}
