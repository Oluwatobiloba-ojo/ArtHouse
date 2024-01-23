package org.example.data.repository;

import org.example.data.model.Art;
import org.example.data.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

}
