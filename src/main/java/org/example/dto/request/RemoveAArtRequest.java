package org.example.dto.request;

import lombok.Data;

@Data
public class RemoveAArtRequest {
    private String email;
    private Long artId;
}
