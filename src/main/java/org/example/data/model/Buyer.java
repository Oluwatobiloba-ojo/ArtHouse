package org.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Buyer {
    @Id
    private Long id;
    private String username;
    private String password;
    private boolean isEnable;
    private String email;
}

