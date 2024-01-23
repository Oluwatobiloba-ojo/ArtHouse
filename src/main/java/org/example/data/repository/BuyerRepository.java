package org.example.data.repository;

import org.example.data.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    Buyer findByUsername(String buyerName);
}
