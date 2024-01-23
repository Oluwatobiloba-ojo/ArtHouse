package org.example.services;

import org.example.data.model.Artist;
import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.dto.request.FindAArtRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.RemoveAArtRequest;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Artist register(RegisterRequest registerRequest);

    List<Art> findArtsBelongingTo(String email);

    Art findAArt(FindAArtRequest findAArtRequest);


    void removeAArt(RemoveAArtRequest removeAArtRequest);



}
