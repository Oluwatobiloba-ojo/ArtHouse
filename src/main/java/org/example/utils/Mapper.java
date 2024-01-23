package org.example.utils;

import org.example.data.model.Artist;
import org.example.dto.request.RegisterRequest;

public class Mapper {

    public  static Artist artistMapper(RegisterRequest registerRequest){
     Artist artist = new Artist();
     artist.setUsername(registerRequest.getUsername());
     artist.setPassword(registerRequest.getPassword());
     artist.setEmail(registerRequest.getEmail());
        return artist;
    }


}
