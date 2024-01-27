package org.example.exceptions;

public class ArtNotFound extends RuntimeException {
    public ArtNotFound(String message) {
        super(message);
    }
}
