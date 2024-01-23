package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.data.repository.ArtistRepository;
import org.example.dto.request.FindAArtRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.RemoveAArtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtServices artServices;
    private ArrayList<Art> listOfArts = new ArrayList<>();

    @Override
    public void register(RegisterRequest registerRequest) {


    }

    @Override
    public List<Art> findArtsBelongingTo(String email) {
        Optional<Artist> artist = artistRepository.findByEmail(email);
        for (Art art : artServices.findAllArt()) {
            if (art.getArtist().getId().equals(artist.get().getId())) {
                listOfArts.add(art);
            }
        }
        return listOfArts;
    }

    @Override
    public Art findAArt(FindAArtRequest findAArtRequest) {
        Optional<Artist> artist = artistRepository.findByEmail(findAArtRequest.getEmail());
        if (artist.isPresent()) {
            return artServices.findAArt(findAArtRequest.getArtId());
        }
        return null;
    }

    @Override
    public void removeAArt(RemoveAArtRequest removeAArtRequest) {
        Optional<Artist> artist = artistRepository.findByEmail(removeAArtRequest.getEmail());
        if (artist.isPresent()) {
            artServices.removeAArt(removeAArtRequest.getArtId());
        }
    }

}

