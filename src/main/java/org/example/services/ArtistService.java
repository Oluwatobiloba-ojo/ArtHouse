package org.example.services;

import org.example.data.model.Artist;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;

public interface ArtistService {


    Artist register(RegisterRequest registerRequest);
    void  login(LoginRequest loginRequest);
}
