package com.akirolabs.token.generatorservice.Exception;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 12-08-2023
 */
public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super();
    }
    public BadRequestException(String message) {
        super(message);
    }
}
