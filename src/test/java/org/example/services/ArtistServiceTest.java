package org.example.services;

import org.example.data.repository.ArtRepository;
import org.example.data.repository.ArtistRepository;
import org.example.dto.request.DisplayArtRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exceptions.ArtistExistException;
import org.example.exceptions.InvalidEmailException;
import org.example.exceptions.InvalidPasswordException;
import org.example.exceptions.InvalidUsernameException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtistServiceTest {

    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtRepository artRepository;
    @AfterEach
    public void deleteBeforeTest(){
        artistRepository.deleteAll();
        artRepository.deleteAll();
    }
    @Test
    public void testThatAnArtist_CanRegisterWithWrongUsernameThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("star girl8");
        registerRequest.setPassword("joy,1234");
        registerRequest.setEmail("joytim7277@gmail.com");
        assertThrows(InvalidUsernameException.class,()->artistService.register(registerRequest));
    }
    @Test
    public void testWhenArtistRegisterWithWrongPasswordThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("ada");
        registerRequest.setEmail("joytim7277@gmail.com");
        assertThrows(InvalidPasswordException.class, ()->artistService.register(registerRequest));
    }
    @Test
    public void testThatWhenArtistRegisterWithWrongEmailThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("OlaPrecious");
        registerRequest.setEmail("joyt");
        assertThrows(InvalidEmailException.class, ()->artistService.register(registerRequest));
    }
    @Test
    public void testThatArtistRegisterWithCorrectInfoReturnsArtistObjects(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("OlaPrecious");
        registerRequest.setEmail("joy@gmail.com");
        artistService.register(registerRequest);
        assertEquals(1, artistRepository.count());
    }
    @Test
    public void testThatArtistCanRegisterAgainWithCorrectInfoReturnsArtistObjects(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("OlaPrecious");
        registerRequest.setEmail("joy@gmail.com");
        artistService.register(registerRequest);
        assertThrows(ArtistExistException.class,()->artistService.register(registerRequest));
    }

    @Test
    public void testThatArtistCan_RegisterAndLogin(){
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setUsername("Sandra");
        registerRequest.setPassword("Olaoluwajohn");
        registerRequest.setEmail("joy@gmail.com");
        artistService.register(registerRequest);
        loginRequest.setUsername("Sandra");
        loginRequest.setPassword("Olaoluwajohn");
        loginRequest.setEmail("joy@gmail.com");
        artistService.login(loginRequest);
    }
    @Test
    public void testThatArtistCanDisplayArtToTheTheArtGalleryAndArtRepositoryIncreaseToOne(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Sandra");
        registerRequest.setPassword("Olaoluwajohn");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        artistService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Sandra");
        loginRequest.setPassword("Olaoluwajohn");
        loginRequest.setEmail("deborahdelighted5@gmail.com");
        artistService.login(loginRequest);
        DisplayArtRequest displayArtRequest = new DisplayArtRequest();
        displayArtRequest.setArtistUsername("Sandra");
        displayArtRequest.setArtName("Monkey on the tree");
        displayArtRequest.setDescription("A paint picture which is monkey is on the tree");
        displayArtRequest.setAmount(BigDecimal.valueOf(3000));
        artistService.displayArt(displayArtRequest);
        assertEquals(1, artRepository.count());
    }
    @Test
    public void testThatWhenYouRegisterTwoArtistAndOneOfTheArtistDisplayAnArtItOnlyBelongsToThatArtist(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Sandra");
        registerRequest.setPassword("Olaoluwajohn");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        artistService.register(registerRequest);
        registerRequest.setUsername("Delight");
        artistService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Sandra");
        loginRequest.setPassword("Olaoluwajohn");
        loginRequest.setEmail("joy@gmail.com");
        artistService.login(loginRequest);
        loginRequest.setUsername("Delight");
        artistService.login(loginRequest);
        DisplayArtRequest displayArtRequest = new DisplayArtRequest();
        displayArtRequest.setArtistUsername("Sandra");
        displayArtRequest.setArtName("Monkey on the tree");
        displayArtRequest.setDescription("A paint picture which is monkey is on the tree");
        displayArtRequest.setAmount(BigDecimal.valueOf(3000));
        artistService.displayArt(displayArtRequest);
        assertEquals(1, artRepository.count());
        assertEquals(1, artistService.findAllArt("Sandra").size());
        assertEquals(0, artistService.findAllArt("Delight").size());
    }




}