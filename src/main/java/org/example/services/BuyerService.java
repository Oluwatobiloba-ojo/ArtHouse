package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Buyer;

import java.util.List;

import org.example.data.model.Buyer;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.PurchaseArtRequest;
import org.example.dto.request.RegisterRequest;


import org.example.data.model.Art;

import java.util.List;

public interface BuyerService {
    List<Art> viewAllPublishedArt(String buyerName,String email);
//List<Art> viewAllArt();
//    void purchaseArt(String email, int artId, int amount);

    Buyer register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);
    void purchase(PurchaseArtRequest purchaseArtRequest);
}
