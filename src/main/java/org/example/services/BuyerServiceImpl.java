package org.example.services;

import org.example.data.model.Buyer;
import org.example.data.repository.BuyerRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exceptions.*;
import org.example.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.data.model.Art;
import org.example.data.model.Buyer;
import org.example.data.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.utils.Mapper.buyerMapper;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private ArtService artService;

    @Override
    public Buyer register(RegisterRequest registerRequest) {
        if (checkIfBuyerExist(registerRequest.getUsername(), registerRequest.getEmail()))
            throw new BuyerExistException("User already exist");
        validations(registerRequest);
        Buyer buyer = buyerMapper(registerRequest);
        return buyerRepository.save(buyer);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Optional<Buyer> foundBuyer = buyerRepository.findByUsername(loginRequest.getUsername());
        if (!checkIfBuyerExist(loginRequest.getUsername(), loginRequest.getEmail())) {
            throw new BuyerExistException("Invalid details");
        }
        if (!foundBuyer.get().getUsername().equalsIgnoreCase(loginRequest.getUsername())) {
            throw new InvalidDetailsException("Details entered are invalid");
        }
        if (!foundBuyer.get().getPassword().equalsIgnoreCase(loginRequest.getPassword())) {
            throw new InvalidDetailsException("Details entered are invalid");
        }
        if (!foundBuyer.get().getEmail().equalsIgnoreCase(loginRequest.getEmail())) {
            throw new InvalidDetailsException("Details entered are invalid");
        }
        foundBuyer.get().setEnable(false);
        buyerRepository.save(foundBuyer.get());
    }

    public void validations(RegisterRequest registerRequest) {
        if (!Validator.validateName(registerRequest.getUsername())) {
            throw new InvalidUsernameException("Invalid username");
        }
        if (!Validator.validatePassword(registerRequest.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        if (!Validator.validateEmail(registerRequest.getEmail())) {
            throw new InvalidEmailException("Invalid Email");
        }
    }

    public boolean checkIfBuyerExist(String buyerName, String email) {
        return buyerRepository.findByUsername(buyerName).isPresent() && buyerRepository.findByEmail(email).isPresent();
    }

    @Override
    public List<Art> viewAllPublishedArt(String buyerName, String email) {
        ArrayList<Art> publishedArts = new ArrayList<>();
        validateBuyer(email);
        for (Art art : artService.findAllArt()) {
            if (art.isPublished()) {
                publishedArts.add(art);
            }
        }

        return publishedArts;

    }


    private void validateBuyer(String email) {
        Optional<Buyer> buyer = buyerRepository.findByEmail(email);
        if (buyer.isEmpty()) {
            throw new BuyerExistException("Account doesnt exist");
        }
    }
}
