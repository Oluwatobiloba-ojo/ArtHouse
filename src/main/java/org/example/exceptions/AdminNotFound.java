package org.example.exceptions;

public class AdminNotFound extends RuntimeException{
    public AdminNotFound(String message){
        super(message);
    }
}
