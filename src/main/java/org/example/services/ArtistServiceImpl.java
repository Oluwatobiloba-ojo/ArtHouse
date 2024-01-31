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
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    ArtService artService;
    @Autowired
    EmailService emailService;

    @Override
    public Artist register(RegisterRequest registerRequest) {
        if (checkIfArtistExist(registerRequest.getUsername(), registerRequest.getEmail()))
            throw new ArtistExistException("Artist already exist\t" + registerRequest.getUsername());
        validations(registerRequest);
        Artist artist = artistMapper(registerRequest);
        return artistRepository.save(artist);
    }

    @Override
    public Artist login(LoginRequest loginRequest) {
        if (!checkIfArtistExist(loginRequest.getUsername(), loginRequest.getEmail()))
            throw new ArtistExistException("Artist May Not exist");
        Optional<Artist> foundArtist = artistRepository.findByUsername(loginRequest.getUsername());
        if (!foundArtist.get().getPassword().equals(loginRequest.getPassword()))
            throw new InvalidDetailsException("Details entered are invalid");
        foundArtist.get().setEnable(true);
        artistRepository.save(foundArtist.get());
        return foundArtist.get();
    }

    @Override
    public Art displayArt(DisplayArtRequest displayArtRequest) {
        if (!checkIfArtistExist(displayArtRequest.getArtistUsername(), displayArtRequest.getEmail()))
            throw new ArtistExistException("Artist does not exist");
        Optional<Artist> foundArtist = findArtist(displayArtRequest.getArtistUsername());
        if (!foundArtist.get().isEnable()) throw new InvalidLoginDetail("User have not login");
        Art art = artService.create(displayArtRequest, foundArtist.get());
        List<Art> artList = foundArtist.get().getArtList();
        artList.add(art);
        foundArtist.get().setArtList(artList);
        artistRepository.save(foundArtist.get());
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setSenderEmail(foundArtist.get().getEmail());
        emailRequest.setTitle("Request to display Art");
        emailRequest.setMessage(String.format("Art proposal %n" +
                        "Art name: %s%nArt description: %s%nArt Price: %s%nArt Id: %s%nArtist Username:%s",
                art.getName(), art.getDescription(), art.getPrice(), art.getId(), art.getArtist().getUsername()));
        emailRequest.setReceiverEmail(ADMIN_EMAIL);
        emailService.sendMailMessage(emailRequest);
        return art;
    }


    @Override
    public List<Art> findAllArt(String username, String email) {
        if (!checkIfArtistExist(username, email)) throw new ArtistExistException("Artist does not exist");
        Optional<Artist> foundArtist = findArtist(username);
        if (!foundArtist.get().isEnable()) throw new InvalidLoginDetail("Unauthorized request due to invalid login");
        return foundArtist.get().getArtList();
    }

    @Override
    public Art findAArt(FindAArtRequest findAArtRequest) {
        Optional<Artist> artist = artistRepository.findByEmail(findAArtRequest.getEmail());
        if (artist.isPresent()) {
            return artService.findAArt(findAArtRequest.getArtId());
        }
        throw new ArtNotFoundException("Art not available");
    }

    @Override
    public void removeAArt(RemoveAArtRequest removeAArtRequest) {
        Optional<Artist> artist = artistRepository.findByEmail(removeAArtRequest.getEmail());
        if (artist.isPresent()) {
            artService.removeAArt(removeAArtRequest.getArtId());
        }
    }


    @Override
    public Optional<Artist> findArtist(String artistUsername) {
        return Optional.of(artistRepository.findByUsername(artistUsername).get());
    }

    public void validations(RegisterRequest registerRequest) {
        if (!Validator.validateName(registerRequest.getUsername()))
            throw new InvalidUsernameException("Invalid username");
        if (!Validator.validatePassword(registerRequest.getPassword()))
            throw new InvalidPasswordException("Invalid password");
        if (!Validator.validateEmail(registerRequest.getEmail())) throw new InvalidEmailException("Invalid Email");
    }

    public boolean checkIfArtistExist(String artistUsername, String email) {
        return artistRepository.findByUsername(artistUsername).isPresent() && artistRepository.findByEmail(email).isPresent();
    }

    @Override
    public void remove(String username, String email) {
        List<Art> arts = findAllArt(username, email);
        artService.delete(arts);
        Optional<Artist> artist = findArtistEmail(email);
        if (artist.isEmpty()) throw new UserNotFound("Error! Artist with this email is not found");
        artistRepository.delete(artist.get());
    }

    @Override
    public Optional<Artist> findArtistEmail(String email) {
        return artistRepository.findByEmail(email);
    }


}


