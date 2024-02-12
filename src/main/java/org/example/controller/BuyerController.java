package org.example.controller;

import org.example.data.model.Buyer;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.PurchaseArtRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.*;
import org.example.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;
    @PostMapping("/create")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        BuyerRegisterResponse registerResponse = new BuyerRegisterResponse();
        try {
            Buyer buyer = buyerService.register(registerRequest);
            registerResponse.setMessage("Buyer info is \n" + buyer);
            return new ResponseEntity<>(new ApiResponse(true, registerResponse), HttpStatus.CREATED);
        }catch (Exception exception){
            registerResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, registerResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        BuyerLoginResponse loginResponse = new BuyerLoginResponse();
        try {
            buyerService.login(loginRequest);
            loginResponse.setMessage("You have login !!!!!!!!");
            return new ResponseEntity<>(new ApiResponse(true, loginResponse), HttpStatus.OK);
        }catch (Exception exception){
            loginResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, loginResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/arts/{name}")
    public ResponseEntity<?> checkOutArt(@PathVariable("name") String name, @RequestParam(name = "email")String email){
        ViewAllArtResponse viewAllArtResponse = new ViewAllArtResponse();
        try {
            viewAllArtResponse.setData(buyerService.viewAllPublishedArt(name, email));
            return new ResponseEntity<>(new ApiResponse(true, viewAllArtResponse), HttpStatus.FOUND);
        }catch (Exception exception){
            viewAllArtResponse.setData(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, viewAllArtResponse), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/art")
    private ResponseEntity<?> purchase(@RequestBody PurchaseArtRequest purchaseArtRequest){
        PurchaseArtResponse artResponse = new PurchaseArtResponse();
        try {
            buyerService.purchase(purchaseArtRequest);
            artResponse.setMessage(String.format("Art with this %s has been bought",purchaseArtRequest.getArtId()));
            return new ResponseEntity<>(new ApiResponse(true, artResponse), HttpStatus.ACCEPTED);
        }catch (Exception exception){
            artResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, artResponse), HttpStatus.BAD_REQUEST);
        }
    }

}
