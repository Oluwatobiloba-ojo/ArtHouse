package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Buyer;
import org.example.data.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private ArtServices artServices;
    private ArrayList<Art> publishedArts = new ArrayList<>();

    @Override
    public List<Art> viewAllPublishedArt(String email) {
        Optional<Buyer> buyer = buyerRepository.findByEmail(email);
        if (buyer.isPresent()) {
            for (Art art : artServices.findAllArt()) {
                if (art.isPublished()) {
                    publishedArts.add(art);
                }
            }
            return publishedArts;
        }

    }

    private boolean validateBuyer(String email){
        Optional<Buyer> buyer = buyerRepository.findByEmail(email);
        return buyer.isPresent();
    }
}
