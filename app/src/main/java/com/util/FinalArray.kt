package com.util

import java.util.Arrays
import java.util.NoSuchElementException

@Suppress("unused")
/**
 * A FinalArray is an array that is immutable.
 *
 * It only supports arrays of objects though, so convert arrays of primitive types
 * into their respective wrapper classes first.
 * For example, passing an int[] would give a compiler error,
 * but passing an Integer[] wouldn't.
 */
class FinalArray<T> private constructor(arr: Array<T>) : Iterable<T> {
    private val arr: Array<T> = Arrays.copyOf(arr, arr.size)

    operator fun get(index: Int): T {
        return arr[index]
    }

    fun length(): Int {
        return arr.size
    }

    override fun toString(): String {
        return Arrays.toString(arr)
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            private var current = 0

            override fun hasNext(): Boolean {
                return current < this@FinalArray.arr.size
            }

            override fun next(): T {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }
                return arr[current++]
            }
        }
    }

    companion object {
        fun <T> from(arr: Array<T>): FinalArray<T> {
            return FinalArray(arr)
        }
    }
}