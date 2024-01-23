package org.example.services;

import org.example.data.model.Art;

import java.util.List;

public interface BuyerService {
    void purchaseArt(String email, int atrId, int amount);

}
