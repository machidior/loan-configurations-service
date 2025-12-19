package com.machidior.configuration_service.exceptions;

public class InvalidEnumException extends IllegalArgumentException {
    public InvalidEnumException(String message) {
        super(message);
    }
}
