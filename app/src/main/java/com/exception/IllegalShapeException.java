package com.exception;

/**
 * An exception that is thrown when an illegal shape is constructed.
 * For example, attempting to construct a triangle that does not exist
 * will result in this exception being thrown.
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