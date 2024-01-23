package org.example.services;

import org.example.data.model.Buyer;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;

public interface BuyerService {

    Buyer register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);
}
