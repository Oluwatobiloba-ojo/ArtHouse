package org.example.services;

import org.example.data.model.Art;
import org.example.dto.request.UploadRequest;

public interface AdminService {
    Art uploadArt(UploadRequest uploadRequest);

    void removeArtist(String username, String email);
}
