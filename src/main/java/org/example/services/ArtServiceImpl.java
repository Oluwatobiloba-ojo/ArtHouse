package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.data.repository.ArtRepository;
import org.example.dto.request.DisplayArtRequest;
import org.example.exceptions.ArtNotFoundException;
import org.example.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtServiceImpl implements ArtService {
    @Autowired
    private ArtRepository artRepository;
    @Override
    public Art create(DisplayArtRequest displayArtRequest, Artist foundArtist) {
        Art art = Mapper.mapArt(displayArtRequest, foundArtist);
        save(art);
        return art;
    }

    @Override
    public void save(Art art) {
        artRepository.save(art);
    }

    @Override
    public List<Art> findAllArt() {
        return artRepository.findAll();
    }

    @Override
    public Art findAArt(Long artId) {
        return artRepository.findById(artId).get();
    }

    @Override
    public void removeAArt(Long artId) {
        Art art = findAArt(artId);
        if (!art.isPublished()) {
            artRepository.delete(findAArt(artId));
        }
        throw new ArtNotFoundException("Art not found");
    }
}

