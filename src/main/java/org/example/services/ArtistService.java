package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.dto.request.DisplayArtRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;

import java.util.List;

public interface ArtistService {


    Artist register(RegisterRequest registerRequest);
    void  login(LoginRequest loginRequest);
    void displayArt(DisplayArtRequest displayArtRequest);
    List<Art> findAllArt(String sandra);
}
