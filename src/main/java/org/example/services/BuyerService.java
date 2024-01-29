package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Buyer;

import java.util.List;

import org.example.data.model.Buyer;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;

public interface BuyerService {
    List<Art> viewAllPublishedArt(String buyerName,String email);

    Buyer register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);
}
