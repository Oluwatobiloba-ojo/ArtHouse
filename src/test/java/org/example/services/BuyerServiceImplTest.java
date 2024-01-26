package org.example.services;

import org.example.data.repository.ArtistRepository;
import org.example.data.repository.BuyerRepository;
import org.example.dto.request.DisplayArtRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exceptions.BuyerExistException;
import org.example.exceptions.InvalidEmailException;
import org.example.exceptions.InvalidPasswordException;
import org.example.exceptions.InvalidUsernameException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BuyerServiceImplTest {
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistRepository artistRepository;

    @AfterEach
    public void deleteBeforeTest() {
        buyerRepository.deleteAll();
        artistRepository.deleteAll();
    }

    @Test
    public void testThatAnArtist_CanRegisterWithWrongUsernameThrowsException() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("star girl8");
        registerRequest.setPassword("joy,1234");
        registerRequest.setEmail("joytim7277@gmail.com");
        assertThrows(InvalidUsernameException.class, () -> buyerService.register(registerRequest));
    }

    @Test
    public void testWhenArtistRegisterWithWrongPasswordThrowsException() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("ada");
        registerRequest.setEmail("joytim7277@gmail.com");
        assertThrows(InvalidPasswordException.class, () -> buyerService.register(registerRequest));
    }

    @Test
    public void testThatWhenArtistRegisterWithWrongEmailThrowsException() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("OlaPrecious");
        registerRequest.setEmail("joyt");
        assertThrows(InvalidEmailException.class, () -> buyerService.register(registerRequest));
    }


    @Test
    public void testThatArtistCanRegisterAgainWithCorrectInfoReturnsArtistObjects() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("OlaPrecious");
        registerRequest.setEmail("joy@gmail.com");
        buyerService.register(registerRequest);
        assertThrows(BuyerExistException.class, () -> buyerService.register(registerRequest));
    }

    @Test
    public void testThatArtistCan_RegisterAndLogin() {
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setUsername("Sandra");
        registerRequest.setPassword("Olaoluwajohn");
        registerRequest.setEmail("joy@gmail.com");
        buyerService.register(registerRequest);
        loginRequest.setUsername("Sandra");
        loginRequest.setPassword("Olaoluwajohn");
        loginRequest.setEmail("joy@gmail.com");
        buyerService.login(loginRequest);
    }

    @Test
    public void testThatBuyerCanViewAllArtThatArePublished() {
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setUsername("Sandra");
        registerRequest.setPassword("Olaoluwajohn");
        registerRequest.setEmail("joy@gmail.com");
        buyerService.register(registerRequest);
        loginRequest.setUsername("Sandra");
        loginRequest.setPassword("Olaoluwajohn");
        loginRequest.setEmail("joy@gmail.com");
        buyerService.login(loginRequest);
        RegisterRequest registerRequestArtist = new RegisterRequest();
        registerRequestArtist.setUsername("Iyanu");
        registerRequestArtist.setPassword("Olaoluwajohn");
        registerRequestArtist.setEmail("deborahdelighted5@gmail.com");
        artistService.register(registerRequestArtist);
        LoginRequest loginRequestArtist = new LoginRequest();
        loginRequestArtist.setUsername("Iyanu");
        loginRequestArtist.setPassword("Olaoluwajohn");
        loginRequestArtist.setEmail("deborahdelighted5@gmail.com");
        artistService.login(loginRequestArtist);
        DisplayArtRequest displayArtRequest = new DisplayArtRequest();
        displayArtRequest.setArtistUsername("Iyanu");
        displayArtRequest.setArtName("Monkey on the tree");
        displayArtRequest.setDescription("A paint picture which is monkey is on the tree");
        displayArtRequest.setAmount(BigDecimal.valueOf(3000));
        artistService.displayArt(displayArtRequest);
        DisplayArtRequest displayArtRequest1 = new DisplayArtRequest();
        displayArtRequest1.setArtistUsername("Iyanu");
        displayArtRequest1.setArtName("The theme team");
        displayArtRequest1.setDescription("A paint picture that endorse team");
        displayArtRequest1.setAmount(BigDecimal.valueOf(2500));
        artistService.displayArt(displayArtRequest1);
        DisplayArtRequest displayArtRequest2 = new DisplayArtRequest();
        displayArtRequest2.setArtistUsername("Iyanu");
        displayArtRequest2.setArtName("Merge peg");
        displayArtRequest2.setDescription("A paint picture reference collaboration");
        displayArtRequest2.setAmount(BigDecimal.valueOf(4000));
        artistService.displayArt(displayArtRequest2);
        assertEquals(3, artistService.findAllArt("Iyanu").size());
        assertEquals(3, buyerService.viewAllPublishedArt("joy@gmail.com","joy@gmail.com").size());

    }
}