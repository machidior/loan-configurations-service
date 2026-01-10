package com.machidior.configuration_service.exceptions;

public class ImmutableVersionException extends RuntimeException {
    public ImmutableVersionException(String message) {
        super(message);
    }
}
