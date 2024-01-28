package org.example.dto.request;

import lombok.Data;

@Data
public class UploadRequest {
    private long artId;
    private long artistId;
    private String email;
}