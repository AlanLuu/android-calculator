package com.exception;

/**
 * An exception that is thrown when an illegal shape is constructed.
 */
@SuppressWarnings("unused")
public class IllegalShapeException extends RuntimeException {
    public IllegalShapeException() {
        super();
    }

    public IllegalShapeException(String s) {
        super(s);
    }
}