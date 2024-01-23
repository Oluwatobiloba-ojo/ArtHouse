package org.example.utils;

import org.example.data.model.Artist;
import org.example.data.model.Buyer;
import org.example.dto.request.RegisterRequest;

public class Mapper {

    public  static Artist artistMapper(RegisterRequest registerRequest){
     Artist artist = new Artist();
     artist.setUsername(registerRequest.getUsername());
     artist.setPassword(registerRequest.getPassword());
     artist.setEmail(registerRequest.getEmail());
        return artist;
    }

    public  static Buyer buyerMapper(RegisterRequest registerRequest){
        Buyer buyer = new Buyer();
        buyer.setUsername(registerRequest.getUsername());
        buyer.setPassword(registerRequest.getPassword());
        buyer.setEmail(registerRequest.getEmail());
        return buyer;
    }
}
