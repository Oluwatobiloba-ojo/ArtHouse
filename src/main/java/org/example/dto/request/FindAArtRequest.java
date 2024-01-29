package org.example.dto.request;

import lombok.Data;

@Data
public class FindAArtRequest {
    private String email;
    private Long artId;
}
