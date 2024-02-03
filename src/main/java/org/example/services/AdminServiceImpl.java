package org.example.services;

import org.example.data.model.Admin;
import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.data.repository.AdminRepository;
import org.example.dto.request.AdminRequest;
import org.example.dto.request.RemoveArtistRequest;
import org.example.dto.request.UploadRequest;
import org.example.exceptions.AdminNotFound;
import org.example.exceptions.ArtNotFound;
import org.example.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    ArtistService artistService;
    @Autowired
    ArtService artService;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Art uploadArt(AdminRequest adminRequest, UploadRequest uploadRequest) {
        confirmAdmin(adminRequest.getUsername());
        Optional<Artist> artist = artistService.findArtistEmail(uploadRequest.getEmail());
        if (artist == null) throw new UserNotFound("Error! Artist with this email is not found");
        Art art = artService.findArt(uploadRequest.getArtId());
        if (art == null) throw new ArtNotFound("Art belonging to this id not found");
        art.setPublished(true);
        artService.save(art);
        return art;
    }

    @Override
    public void removeArtist(AdminRequest adminRequest, RemoveArtistRequest removeArtistRequest) {
        confirmAdmin(adminRequest.getUsername());

        Optional<Artist> artist = artistService.findArtist(removeArtistRequest.getUsername());
        if (artist == null) throw new UserNotFound("Error! Artist with this email is not found");

        artistService.remove(removeArtistRequest.getUsername(), removeArtistRequest.getEmail());
    }

    @Override
    public void confirmAdmin(String username) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) throw new AdminNotFound("Error! Admin not found.");

    }

}
