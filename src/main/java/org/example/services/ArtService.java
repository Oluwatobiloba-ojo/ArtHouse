package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.dto.request.DisplayArtRequest;

import java.util.List;

public interface ArtService {
    Art create(DisplayArtRequest displayArtRequest, Artist foundArtist);
    void save(Art art);
    List<Art> findAllArt();
    Art findAArt(Long artId);
    void removeAArt(Long artId);

}
