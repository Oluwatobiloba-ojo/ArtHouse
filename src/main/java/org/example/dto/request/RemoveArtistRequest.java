package org.example.dto.request;

import lombok.Data;

@Data
public class RemoveArtistRequest {
    private String username;
    private String email;
}
