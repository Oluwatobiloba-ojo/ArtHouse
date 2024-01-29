package org.example.services;

import org.example.data.model.Admin;
import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.data.repository.ArtRepository;
import org.example.dto.request.AdminRequest;
import org.example.dto.request.RemoveArtistRequest;
import org.example.dto.request.UploadRequest;
import org.example.exceptions.ArtNotFound;
import org.example.exceptions.InvalidDetailsException;
import org.example.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.example.utils.Mapper.mapAdmin;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    ArtistService artistService;
    @Autowired
    ArtService artService;
    @Autowired
    ArtRepository artRepository;

    @Override
    public Art uploadArt(AdminRequest adminRequest, UploadRequest uploadRequest) {
        confirmAdmin(adminRequest, adminRequest.getEmail(), adminRequest.getPassword());
        Optional<Artist> artist = artistService.findArtistEmail(uploadRequest.getEmail());
        if (artist == null) throw new UserNotFound("Error! Artist with this email is not found");
        Art art = artService.findArt(uploadRequest.getArtId());
        if (art == null) throw new ArtNotFound("Art belonging to this id not found");
        art.setPublished(true);
        artRepository.save(art);
        return art;
    }

    @Override
    public void removeArtist(AdminRequest adminRequest, RemoveArtistRequest removeArtistRequest) {
        confirmAdmin(adminRequest, adminRequest.getEmail(), adminRequest.getPassword());

        Optional<Artist> artist = artistService.findArtist(removeArtistRequest.getUsername());
        if (artist == null) throw new UserNotFound("Error! Artist with this email is not found");

        artistService.remove(removeArtistRequest.getUsername(), removeArtistRequest.getEmail());
    }

    @Override
    public void confirmAdmin(AdminRequest adminRequest, String email, String password) {
        Admin admin = mapAdmin(email, password);
        if (!adminRequest.getEmail().equals(email) && !adminRequest.getPassword().equals(password)){
            throw new InvalidDetailsException("Email or password incorrect");
        }

    }

}
