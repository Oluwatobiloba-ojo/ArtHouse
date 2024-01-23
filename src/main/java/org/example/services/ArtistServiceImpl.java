package org.example.services;

import org.example.data.model.Artist;
import org.example.data.repository.ArtistRepository;
import org.example.dto.request.RegisterRequest;
import org.example.exceptions.ArtistExistException;
import org.example.exceptions.InvalidEmailException;
import org.example.exceptions.InvalidPasswordException;
import org.example.exceptions.InvalidUsernameException;
import org.example.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.example.utils.Mapper.artistMapper;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Autowired
    private ArtistRepository artistRepository;
    @Override
    public Artist register(RegisterRequest registerRequest) {
     if (checkIfArtistExist(registerRequest.getUsername()))
        throw new ArtistExistException("class Artist exist");
     validations(registerRequest);
     Artist artist = artistMapper(registerRequest);
     return artistRepository.save(artist);
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
    public boolean checkIfArtistExist(String artistName){
        Artist artist = artistRepository.findByUsername(artistName);
        if (artist == null) return false;
        else return true;
    }
}
