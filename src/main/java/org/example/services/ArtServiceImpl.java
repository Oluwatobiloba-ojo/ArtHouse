package org.example.services;

import org.example.data.model.Art;
import org.example.data.repository.ArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtServiceImpl implements ArtService{
    @Autowired
    ArtRepository artRepository;

    @Override
    public void save(Art art) {
        artRepository.save(art);
    }

    @Override
    public Art findArt(long artId) {
        Art art = artRepository.findArtById(artId);
        return art;
    }

    @Override
    public Long count() {
        return artRepository.count();
    }

    @Override
    public List<Art> findArtsBelongingTo(String email) {
        return artRepository.findArtsByArtist_Email(email);
    }
}
