package org.example.services;

import org.example.data.model.Art;

import org.example.dto.request.AdminRequest;
import org.example.dto.request.RemoveArtistRequest;
import org.example.dto.request.UploadRequest;

public interface AdminService {
    Art uploadArt(AdminRequest adminRequest, UploadRequest uploadRequest);

    void removeArtist(AdminRequest adminRequest, RemoveArtistRequest removeArtistRequest);


    void confirmAdmin(AdminRequest adminRequest, String email, String password);
}
