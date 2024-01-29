package org.example.exceptions;

public class ArtNotFoundException extends RuntimeException{
    public ArtNotFoundException(String message){
        super(message);
    }
}
