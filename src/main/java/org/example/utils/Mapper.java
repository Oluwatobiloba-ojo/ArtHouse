package org.example.utils;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.data.model.Buyer;
import org.example.dto.request.DisplayArtRequest;
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

    public static Art mapArt(DisplayArtRequest displayArtRequest, Artist foundArtist) {
        Art art = new Art();
        art.setArtist(foundArtist);
        art.setDescription(displayArtRequest.getDescription());
        art.setName(displayArtRequest.getArtName());
        art.setPrice(displayArtRequest.getAmount());
        return art;
    }

    public static Art map(String name, long id){
        Art art = new Art();
        art.setName(name);
        art.setId(id);
        return art;
    }
}
