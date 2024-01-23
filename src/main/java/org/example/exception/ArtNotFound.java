package org.example.exception;

public class ArtNotFound extends RuntimeException {
    public ArtNotFound(String message) {
        super(message);
    }
}
