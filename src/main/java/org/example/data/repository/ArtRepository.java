package org.example.data.repository;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtRepository extends JpaRepository<Art, Long> {
    Art findArtByIdAndArtist(long Id, Artist artistId);
    Art findArtById(long id);
    List<Art> findArtsByArtist_Email(String email);
}
