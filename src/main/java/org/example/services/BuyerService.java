package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Buyer;

import java.util.List;

public interface BuyerService {
    List<Art> viewAllPublishedArt(String email);
    Buyer findBuyerByEmail(String email);

}
