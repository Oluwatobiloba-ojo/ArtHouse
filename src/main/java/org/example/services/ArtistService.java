package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.dto.request.*;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Artist register(RegisterRequest registerRequest);

    Optional<Artist> findArtist(String email);

    void remove(String username, String email);

    Artist login(LoginRequest loginRequest);

    Art displayArt(DisplayArtRequest displayArtRequest);

    Optional<Artist> findArtistEmail(String email);

    List<Art> findAllArt(String sandra, String email);

    Art findAArt(FindAArtRequest findAArtRequest);

    void removeAArt(RemoveAArtRequest removeAArtRequest);


}
