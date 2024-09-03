package com.example.exception;

import lombok.Getter;

@Getter
public class LibraryException extends RuntimeException {

    private final String message;

    public LibraryException(String message) {
        super(message);
        this.message = message;
    }
}
