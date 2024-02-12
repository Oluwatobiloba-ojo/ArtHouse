package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.dto.request.*;

import java.util.List;
import java.util.Optional;
import java.util.Optional;

public interface ArtistService {


    Artist register(RegisterRequest registerRequest);
    void  login(LoginRequest loginRequest);
    Art displayArt(DisplayArtRequest displayArtRequest);
    List<Art> findAllArt(String sandra, String email);
    Optional<Artist> findArtist(String email);
    Optional<Artist> findArtistEmail(String email);
    void remove(String username, String email);
    void removeAArt(RemoveAArtRequest removeAArtRequest);
    Art findAArt(FindAArtRequest findAArtRequest);
}
