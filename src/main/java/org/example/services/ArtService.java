package org.example.services;

import org.example.data.model.Art;

import java.util.List;

import org.example.data.model.Artist;
import org.example.dto.request.DisplayArtRequest;

import java.util.List;

public interface ArtService {

    Art findArt(long artId);

    Long count();

    List<Art> findArtsBelongingTo(String email);

    Art create(DisplayArtRequest displayArtRequest, Artist foundArtist);
    void delete(List<Art> arts);

    void save(Art art);
    List<Art> findAllArt();
    Art findAArt(Long artId);
    void removeAArt(Long artId);

}
