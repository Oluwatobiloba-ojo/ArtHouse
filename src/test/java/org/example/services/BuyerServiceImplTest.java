package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Artist;
import org.example.data.repository.ArtistRepository;
import org.example.data.repository.BuyerRepository;
import org.example.dto.request.*;
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
    private AdminService adminService;
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
        Artist artist = artistService.login(loginRequestArtist);
        DisplayArtRequest displayArtRequest = new DisplayArtRequest();
        displayArtRequest.setArtistUsername("Iyanu");
        displayArtRequest.setEmail("deborahdelighted5@gmail.com");
        displayArtRequest.setArtName("Monkey on the tree");
        displayArtRequest.setDescription("A paint picture which is monkey is on the tree");
        displayArtRequest.setAmount(BigDecimal.valueOf(3000));
       Art art = artistService.displayArt(displayArtRequest);
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setUsername("Admin");
        adminRequest.setPassword("admin12");
        UploadRequest uploadRequest = new UploadRequest();
        uploadRequest.setArtId(art.getId());
        uploadRequest.setEmail("deborahdelighted5@gmail.com");
        adminService.uploadArt(adminRequest,uploadRequest);
        DisplayArtRequest displayArtRequest1 = new DisplayArtRequest();
        displayArtRequest1.setEmail("deborahdelighted5@gmail.com");
        displayArtRequest1.setArtistUsername("Iyanu");
        displayArtRequest1.setArtName("The theme team");
        displayArtRequest1.setDescription("A paint picture that endorse team");
        displayArtRequest1.setAmount(BigDecimal.valueOf(2500));
        Art art1 = artistService.displayArt(displayArtRequest1);
        UploadRequest uploadRequest1 = new UploadRequest();
        uploadRequest1.setArtId(art1.getId());
        uploadRequest1.setEmail("deborahdelighted5@gmail.com");
        adminService.uploadArt(adminRequest,uploadRequest1);
        DisplayArtRequest displayArtRequest2 = new DisplayArtRequest();
        displayArtRequest2.setEmail("deborahdelighted5@gmail.com");
        displayArtRequest2.setArtistUsername("Iyanu");
        displayArtRequest2.setArtName("Merge peg");
        displayArtRequest2.setDescription("A paint picture reference collaboration");
        displayArtRequest2.setAmount(BigDecimal.valueOf(4000));
        Art art2 = artistService.displayArt(displayArtRequest2);
        UploadRequest uploadRequest2 = new UploadRequest();
        uploadRequest2.setArtId(art2.getId());
        uploadRequest2.setEmail("deborahdelighted5@gmail.com");
        adminService.uploadArt(adminRequest,uploadRequest2);
        assertEquals(3, artistService.findAllArt("Iyanu","deborahdelighted5@gmail.com").size());
        assertEquals(3, buyerService.viewAllPublishedArt("Sandra","joy@gmail.com").size());

    }
}