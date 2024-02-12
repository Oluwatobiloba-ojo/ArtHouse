package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean isEnable=false;
    private String email;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "artist", fetch = FetchType.EAGER, orphanRemoval = true)
//    private List<Art> artList;
}
