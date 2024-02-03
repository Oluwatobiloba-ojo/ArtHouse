package org.example.controller;

import org.example.dto.request.AdminRequest;
import org.example.dto.request.RemoveArtistRequest;
import org.example.dto.request.UploadRequest;
import org.example.dto.response.ApiResponse;
import org.example.dto.response.ArtistResponse;
import org.example.dto.response.UploadResponse;
import org.example.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("Admin/upload-art")
    public ResponseEntity<?> uploadArt(@RequestBody AdminRequest adminRequest,@RequestBody UploadRequest uploadRequest){
        UploadResponse uploadResponse = new UploadResponse();

        try {
            adminService.uploadArt(adminRequest, uploadRequest);
            uploadResponse.setMessage("Art with Id " + uploadRequest.getArtId() +  "has been uploaded successfully");
            return new ResponseEntity<>(new ApiResponse(true,uploadResponse), HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            uploadResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,uploadResponse),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("Admin/remove-artist")
    public ResponseEntity<?> removeArtist(@RequestBody AdminRequest adminRequest,@RequestBody RemoveArtistRequest removeArtistRequest){
        ArtistResponse removeArtistResponse = new ArtistResponse();

        try {
            adminService.removeArtist(adminRequest, removeArtistRequest);
            removeArtistResponse.setMessage(removeArtistRequest.getUsername() + " and all his/her arts has been removed from Art House");
            return new ResponseEntity<>(new ApiResponse(true,removeArtistResponse), HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            removeArtistResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,removeArtistResponse),HttpStatus.BAD_REQUEST);
        }
    }
}
