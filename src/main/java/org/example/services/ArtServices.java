package org.example.services;

import org.example.data.model.Art;

import java.util.List;

public interface ArtServices {
    List<Art> findAllArt();
    Art findAArt(Long artId);
    void removeAArt(Long artId);
}
