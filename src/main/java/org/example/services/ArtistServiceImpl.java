package org.example.services;

import org.example.data.model.Artist;
import org.example.data.repository.ArtistRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exceptions.*;
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
        throw new ArtistExistException("Artist already exist\t"+ registerRequest.getUsername());
     validations(registerRequest);
     Artist artist = artistMapper(registerRequest);
     return artistRepository.save(artist);
    }

    @Override
    public void login(LoginRequest loginRequest) {
     Artist foundArtist = artistRepository.findByUsername(loginRequest.getUsername());
     if (!checkIfArtistExist(loginRequest.getUsername())) {
       throw new ArtistExistException("Artist May Not exist") ;
     }
     if (!foundArtist.getUsername().equalsIgnoreCase(loginRequest.getUsername())){
        throw new InvalidDetailsException("Details entered are invalid");
     }
        if (!foundArtist.getPassword().equalsIgnoreCase(loginRequest.getPassword())){
            throw new InvalidDetailsException("Details entered are invalid");
        }
        if (!foundArtist.getEmail().equalsIgnoreCase(loginRequest.getEmail())){
            throw new InvalidDetailsException("Details entered are invalid");
        }
        foundArtist.setEnable(false);
        artistRepository.save(foundArtist);
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
        if (artist == null) {
            return false;}
        else return true;
    }
}
