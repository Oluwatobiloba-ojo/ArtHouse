package org.example.services;

import org.example.data.model.Art;

import java.util.List;

public interface ArtService {
    void save(Art art);

    Art findArt(long artId);

    Long count();

    List<Art> findArtsBelongingTo(String email);
import org.example.data.model.Artist;
import org.example.dto.request.DisplayArtRequest;

public interface ArtService {
    Art create(DisplayArtRequest displayArtRequest, Artist foundArtist);

    void save(Art art);
}
