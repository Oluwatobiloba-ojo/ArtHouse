package org.example.exceptions;

import jakarta.persistence.criteria.CriteriaBuilder;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String message){
        super(message);
    }
}
