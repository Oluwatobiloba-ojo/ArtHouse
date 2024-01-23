package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Buyer;
import org.example.data.repository.BuyerRepository;
import org.example.dto.request.FindByEmail;
import org.example.dto.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {
    private AdminService adminService;
    private final FindByEmail findByEmail = new FindByEmail();
    private final List<RegisterRequest> registerRequests = new ArrayList<>();
    @Autowired
    private  BuyerService buyerService;


    @Override
    public void purchaseArt(String email, int artId, int amount) {
            Buyer buyer = getBuyerByEmail(email);
            if (buyer != null && buyer.isEnable()) {

        }

    }


    private Buyer getBuyerByEmail(String email) {
        return (Buyer) findByEmail.findByEmail(email);
    }
}
