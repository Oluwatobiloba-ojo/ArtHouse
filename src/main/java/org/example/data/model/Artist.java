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
    @OneToMany(mappedBy = "artist",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Art> artList;
}
