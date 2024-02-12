package org.example.dto.response;

import lombok.Data;
import org.example.data.model.Artist;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FindAArtResponse {
    private Long id;
    private BigDecimal amount;
    private String artName;
    private String description;
    private LocalDate date;
    private String artistName;
}
