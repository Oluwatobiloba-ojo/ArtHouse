package org.example.controller;

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
    public ResponseEntity<?> uploadArt(@RequestBody UploadRequest uploadRequest){
        UploadResponse uploadResponse = new UploadResponse();

        try {
            adminService.uploadArt(uploadRequest);
            uploadResponse.setMessage("Art with Id " + uploadRequest.getArtId() + "displayed by artist with Id " + uploadRequest.getArtistId() + "has been uploaded successfully");
            return new ResponseEntity<>(new ApiResponse(true,uploadResponse), HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            uploadResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,uploadResponse),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("Admin/remove-artist{username}/{email}")
    public ResponseEntity<?> removeArtist(@PathVariable String username,  @PathVariable String email){
        ArtistResponse removeArtistResponse = new ArtistResponse();

        try {
            adminService.removeArtist(username, email);
            removeArtistResponse.setMessage(username + " and all his/her arts has been removed from Art House");
            return new ResponseEntity<>(new ApiResponse(true,removeArtistResponse), HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            removeArtistResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,removeArtistResponse),HttpStatus.BAD_REQUEST);
        }
    }
}
