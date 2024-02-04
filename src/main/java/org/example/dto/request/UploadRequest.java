package org.example.dto.request;

import lombok.Data;

@Data
public class UploadRequest {
    private long artId;
    private String artName;
    private String email;
}