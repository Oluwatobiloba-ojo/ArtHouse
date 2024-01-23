package org.example.services;

import org.example.data.model.Buyer;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;


import org.example.data.model.Art;

import java.util.List;

public interface BuyerService {
List<Art> viewAllArt();

    Buyer register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);
}
