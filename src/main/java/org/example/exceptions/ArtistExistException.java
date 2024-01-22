package org.example.exceptions;

public class ArtistExistException extends RuntimeException{
    public ArtistExistException(){
        super();
    }
    public ArtistExistException(String message){
        super(message);
    }
    public ArtistExistException(String message,Throwable cause){
        super(message, cause);
    }
}
