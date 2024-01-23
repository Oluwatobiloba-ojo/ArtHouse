package org.example.exceptions;

public class BuyerExistException extends RuntimeException{
    public BuyerExistException(){
     super();
    }

    public BuyerExistException(String message){
    super(message);
    }

    public BuyerExistException(String message,Throwable cause){
        super(message,cause);
    }
}
