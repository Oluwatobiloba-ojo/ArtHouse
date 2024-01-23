package org.example.services;

import org.example.data.model.Art;
import org.example.data.repository.ArtRepository;
import org.example.data.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtServicesImpl implements ArtServices {
    @Autowired
    private ArtRepository artRepository;

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
//        throw new ArtNotAvailable;
    }
}
