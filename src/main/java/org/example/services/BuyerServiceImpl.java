package org.example.services;
import org.example.data.model.Buyer;
import org.example.data.repository.BuyerRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exceptions.*;
import org.example.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.example.utils.Mapper.buyerMapper;
@Service
public class BuyerServiceImpl implements BuyerService {
   @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public Buyer register(RegisterRequest registerRequest) {
        if (checkIfBuyerExist(registerRequest.getUsername()))
            throw new BuyerExistException("Buyer already exist\t"+ registerRequest.getUsername());
        validations(registerRequest);
        Buyer buyer = buyerMapper(registerRequest);
        return buyerRepository.save(buyer);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Buyer foundBuyer = buyerRepository.findByUsername(loginRequest.getUsername());
        if (!checkIfBuyerExist(loginRequest.getUsername())) {
            throw new BuyerExistException("Buyer May Not exist") ;
        }
        if (!foundBuyer.getUsername().equalsIgnoreCase(loginRequest.getUsername())){
            throw new InvalidDetailsException("Details entered are invalid");
        }
        if (!foundBuyer.getPassword().equalsIgnoreCase(loginRequest.getPassword())){
            throw new InvalidDetailsException("Details entered are invalid");
        }
        if (!foundBuyer.getEmail().equalsIgnoreCase(loginRequest.getEmail())){
            throw new InvalidDetailsException("Details entered are invalid");
        }
        foundBuyer.setEnable(false);
        buyerRepository.save(foundBuyer);
    }

    public  void  validations(RegisterRequest registerRequest){
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
    public boolean checkIfBuyerExist(String buyerName){
        Buyer buyer = buyerRepository.findByUsername(buyerName);
        if (buyer == null) {
            return false;
        }
        else return true;
    }

}
