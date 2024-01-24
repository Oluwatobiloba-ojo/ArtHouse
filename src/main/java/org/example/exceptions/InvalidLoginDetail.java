package org.example.exceptions;

public class InvalidLoginDetail extends RuntimeException{

    public InvalidLoginDetail(String message){
        super(message);
    }
}
