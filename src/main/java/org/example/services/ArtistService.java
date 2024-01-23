package org.example.services;

import org.example.data.model.Artist;
import org.example.dto.request.LoginRequest;
import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.dto.request.RegisterRequest;

public interface ArtistService {
    Artist register(RegisterRequest registerRequest);

    void displayArt(String email, Art art);
    Artist findArtist(String email);
    void remove(String email);
    void  login(LoginRequest loginRequest);


}
