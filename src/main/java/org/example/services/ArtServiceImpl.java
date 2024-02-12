package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.data.repository.ArtRepository;
import org.example.dto.request.DisplayArtRequest;
import org.example.exceptions.ArtNotFoundException;
import org.example.dto.request.PurchaseArtRequest;
import org.example.exceptions.ArtNotFoundException;
import org.example.exceptions.InsufficientAmountException;
import org.example.exceptions.InvalidArtExistException;
import org.example.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class ArtServiceImpl implements ArtService {
    @Autowired
    private ArtRepository artRepository;
    @Override
    public Art create(DisplayArtRequest displayArtRequest, Artist foundArtist) {
        Art art = Mapper.mapArt(displayArtRequest, foundArtist);
        artRepository.save(art);
        return art;
    }

    @Override
    public void delete(List<Art> arts) {
        artRepository.deleteAll(arts);
    }
    @Override
    public void purchaseArt(PurchaseArtRequest purchaseArtRequest) {
        if (!artExist(purchaseArtRequest.getArtId())) throw new ArtNotFoundException("Art does not exist");
        Art art = findArt(purchaseArtRequest.getArtId());
        if (!art.isPublished()) throw new ArtNotFoundException("Art does not exist");
        if (art.getPrice().compareTo(purchaseArtRequest.getAmount()) < 0) throw new InsufficientAmountException("Amount is too low please the amount is  "+ art.getPrice());
        System.out.println(art);
        artRepository.delete(art);
    }

    @Override
    public void save(Art art) {
        artRepository.save(art);
    }

    private boolean artExist(Long artId) {
        return findArt(artId) != null;
    }

    @Override
    public Art findArt(long artId) {
        return artRepository.findArtById(artId);
    }

    @Override
    public Long count() {
        return artRepository.count();
    }

    @Override
    public List<Art> findArtsBelongingTo(String email) {
        return artRepository.findArtsByArtist_Email(email);
    }

    @Override
    public List<Art> findAllArt() {
        return artRepository.findAll();
    }

    @Override
    public Art findAArt(Long artId) {
        if (artRepository.findById(artId).isPresent()){
            return artRepository.findById(artId).get();
        }throw new ArtNotFoundException("Art does not exist");
    }

    @Override
    public void removeAArt(Long artId) {
        Art art = findAArt(artId);
        System.out.println(art);
        if (!art.isPublished()) {
            artRepository.deleteById(artId);
        }else throw new ArtNotFoundException("Art not found");
    }
}

