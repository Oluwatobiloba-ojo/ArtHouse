package org.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Art {

    @Id
    private Long id;
    @OneToOne
    private Artist artist;
    private String name;
    private String description;
    private LocalDate dateUploaded = LocalDate.now();
    private boolean isPublished = false;
    private BigDecimal price;
}
