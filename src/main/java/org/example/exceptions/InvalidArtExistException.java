package org.example.exceptions;

public class InvalidArtExistException extends RuntimeException {

    public InvalidArtExistException(String message){
        super(message);
    }
}
