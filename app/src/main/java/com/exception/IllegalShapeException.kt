package com.exception

import java.lang.RuntimeException

/**
 * An exception that is thrown when an illegal shape is constructed.
 * For example, attempting to construct a triangle that does not exist
 * will result in this exception being thrown.
 */
class IllegalShapeException : RuntimeException {
    constructor() : super()
    constructor(s: String) : super(s)
}