package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.dto.request.DisplayArtRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;

import java.util.List;

public interface ArtistService {


    Artist register(RegisterRequest registerRequest);
    Artist findArtist(String email);
    void remove(String username, String email);
    void  login(LoginRequest loginRequest);
    void displayArt(DisplayArtRequest displayArtRequest);
    List<Art> findAllArt(String sandra);

    Artist findArtistEmail(String email);
    List<Art> findAllArt(String sandra,String email);
    Art findAArt(FindAArtRequest findAArtRequest);
    void removeAArt(RemoveAArtRequest removeAArtRequest);



}
