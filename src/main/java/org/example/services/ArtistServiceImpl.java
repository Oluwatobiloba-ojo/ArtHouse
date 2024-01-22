package org.example.services;

import org.example.data.model.Artist;
import org.example.data.repository.ArtistRepository;
import org.example.dto.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Override
    public void register(RegisterRequest registerRequest) {

    }
}
