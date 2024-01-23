package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.data.repository.ArtRepository;
import org.example.data.repository.ArtistRepository;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.UploadRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    ArtRepository artRepository;


    @Test
    void adminCanUploadArtPresentedByArtistWithValidArtId(){
        RegisterRequest registerRequest = request("username", "password", "vera@gmail.com");
        artistService.register(registerRequest);

        Art art = new Art();
        artistService.displayArt("vera@gmail.com", art);

        UploadRequest uploadRequest = requestUpload(3000L, 9000, "vera@gmail.com");
        art = adminService.uploadArt(uploadRequest);

        assertTrue(art.isPublished());
    }

    @Test
    void adminCanRemoveArtist(){
        RegisterRequest registerRequest1 = request("usernames", "password1", "email@gmail.com");
        artistService.register(registerRequest1);
        assertEquals(1, artistRepository.count());

        adminService.removeArtist("email@gmail.com");
        assertEquals(0, artistRepository.count());
    }

    @Test
    void  allArtsByAnArtistAreRemovedWhenTheArtistIsRemoved(){
        RegisterRequest registerRequest12 = request("username12", "password12", "email12");
        Artist artist12 = artistService.register(registerRequest12);
        RegisterRequest registerRequest13 = request("username13", "password13", "email13");
        Artist artist13 = artistService.register(registerRequest13);

        assertEquals(2, artistRepository.count());

        Art art1 = new Art();
        artistService.displayArt("email12", art1);
        Art art2 = new Art();
        artistService.displayArt("email12", art2);
        Art art3 = new Art();
        artistService.displayArt("email12", art3);
        Art art4 = new Art();
        artistService.displayArt("email13", art4);

        assertEquals(3, artRepository.findArtsByArtist_Email("email12").size());
        assertEquals(1, artRepository.findArtsByArtist_Email("email12").size());

        adminService.removeArtist("email12");
        assertEquals(0, artRepository.findArtsByArtist_Email("email12").size());
        assertEquals(1, artRepository.findArtsByArtist_Email("email12").size());

    }

    private UploadRequest requestUpload(Long artistId, long artId, String email) {
        UploadRequest uploadRequest = new UploadRequest();
        uploadRequest.setArtistId(artistId);
        uploadRequest.setArtId(artId);
        uploadRequest.setEmail(email);
        return uploadRequest;
    }

    private RegisterRequest request(String username, String password, String email) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);
        registerRequest.setEmail(email);
        return registerRequest;
    }
}