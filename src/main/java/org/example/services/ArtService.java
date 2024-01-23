package org.example.services;

import org.example.data.model.Art;

import java.util.List;

public interface ArtService {
    void save(Art art);

    Art findArt(long artId);

    Long count();

    List<Art> findArtsBelongingTo(String email);
}
