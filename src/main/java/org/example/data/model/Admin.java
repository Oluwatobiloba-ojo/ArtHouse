package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Admin  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Artist> artistList;
    private String username;
    private String password;
    private boolean isEnable;
    private String email;
}
