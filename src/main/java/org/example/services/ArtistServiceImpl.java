package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.data.repository.ArtistRepository;
import org.example.dto.request.*;
import org.example.exceptions.*;
import org.example.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.example.Main.ADMIN_EMAIL;
import static org.example.utils.Mapper.artistMapper;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtService artService;
    @Autowired
    private EmailService emailService;

    @Override
    public Artist register(RegisterRequest registerRequest) {
     if (checkIfArtistExist(registerRequest.getUsername())) throw new ArtistExistException("Artist already exist\t"+ registerRequest.getUsername());
     validations(registerRequest);
     Artist artist = artistMapper(registerRequest);
     return artistRepository.save(artist);
    }

    @Override
    public void login(LoginRequest loginRequest) {
     if (!checkIfArtistExist(loginRequest.getUsername())) throw new ArtistExistException("Invalid details");
     Artist foundArtist = artistRepository.findByUsername(loginRequest.getUsername());
     if (!foundArtist.getPassword().equals(loginRequest.getPassword()))throw new InvalidDetailsException("Details entered are invalid");
     foundArtist.setEnable(true);
     artistRepository.save(foundArtist);
    }

    @Override
    public void displayArt(DisplayArtRequest displayArtRequest) {
        if (!checkIfArtistExist(displayArtRequest.getArtistUsername())) throw new ArtistExistException("Artist does not exist");
       Artist foundArtist = findArtist(displayArtRequest.getArtistUsername());
       if (!foundArtist.isEnable()) throw new InvalidLoginDetail("User have not login");
       Art art = artService.create(displayArtRequest, foundArtist);
       List<Art> artList = foundArtist.getArtList();
       artList.add(art);
       foundArtist.setArtList(artList);
       artistRepository.save(foundArtist);
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setSenderEmail(foundArtist.getEmail());
        emailRequest.setTitle("Request to display Art");
        emailRequest.setMessage(String.format("Art proposal %n"+
                "Art name: %s%nArt description: %s%nArt Price: %s%nArt Id: %s%nArtist Username:%s",
                art.getName(), art.getDescription(), art.getPrice(), art.getId(), art.getArtist().getUsername()));
        emailRequest.setReceiverEmail(ADMIN_EMAIL);
        emailService.sendMailMessage(emailRequest);
    }

    @Override
    public List<Art> findAllArt(String username) {
        if (!checkIfArtistExist(username)) throw new ArtistExistException("Artist does not exist");
        Artist foundArtist = findArtist(username);
        if (!foundArtist.isEnable()) throw new InvalidLoginDetail("Unauthorized request due to invalid login");
        return foundArtist.getArtList();
    }

    private Artist findArtist(String artistUsername) {
        return artistRepository.findByUsername(artistUsername);
    }

    public  void  validations(RegisterRequest registerRequest){
         if (!Validator.validateName(registerRequest.getUsername())) throw new InvalidUsernameException("Invalid username");
         if (!Validator.validatePassword(registerRequest.getPassword())) throw new InvalidPasswordException("Invalid password");
         if (!Validator.validateEmail(registerRequest.getEmail())) throw new InvalidEmailException("Invalid Email");
     }
    public boolean checkIfArtistExist(String artistUsername){
        Artist artist = findArtist(artistUsername);
        if (artist == null) {
            return false;
        }
        else return true;

    }
    @Override
    public Art findAArt(FindAArtRequest findAArtRequest) {
        Optional<Artist> artist = artistRepository.findByEmail(findAArtRequest.getEmail());
        if (artist.isPresent()) {
            return artService.findAArt(findAArtRequest.getArtId());
        }
        return null;
    }

    @Override
    public void removeAArt(RemoveAArtRequest removeAArtRequest) {
        Optional<Artist> artist = artistRepository.findByEmail(removeAArtRequest.getEmail());
        if (artist.isPresent()) {
            artService.removeAArt(removeAArtRequest.getArtId());
        }
    }

}
