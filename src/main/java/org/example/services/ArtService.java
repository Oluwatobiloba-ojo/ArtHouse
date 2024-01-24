package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.dto.request.DisplayArtRequest;

public interface ArtService {
    Art create(DisplayArtRequest displayArtRequest, Artist foundArtist);

    void save(Art art);
}
