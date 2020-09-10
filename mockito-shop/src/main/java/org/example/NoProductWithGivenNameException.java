package org.example;

public class NoProductWithGivenNameException extends Exception {

    public NoProductWithGivenNameException(String message) {
        super(message);
    }
}
