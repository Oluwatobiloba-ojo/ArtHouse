package org.example.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@ToString(exclude = "artist")
public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateUploaded = LocalDate.now();

    private boolean isPublished = false;
    private BigDecimal price;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "fk_artist_id")
    @JsonIgnore
    private Artist artist;
}
