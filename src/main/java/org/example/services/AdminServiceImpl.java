package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.dto.request.UploadRequest;
import org.example.exceptions.ArtNotFound;
import org.example.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    ArtistService artistService;
    @Autowired
    ArtService artService;

    @Override
    public Art uploadArt(UploadRequest uploadRequest) {
        Artist artist = artistService.findArtistEmail(uploadRequest.getEmail());
        if (artist == null) throw new UserNotFound("Error! Artist with this email is not found");

        Art art = artService.findArt(uploadRequest.getArtId());
        if (art == null) throw new ArtNotFound("Art belonging to this id not found");

        art.setPublished(true);

        return art;
    }

    @Override
    public void removeArtist(String username, String email) {
        Artist artist = artistService.findArtist(username);
        if (artist == null) throw new UserNotFound("Error! Artist with this email is not found");

        artistService.remove(username, email);
    }
}
