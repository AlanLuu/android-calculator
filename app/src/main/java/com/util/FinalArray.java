package com.util;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A FinalArray is an array that is immutable.
 */
public class FinalArray<T> implements Iterable<T> {
    private T[] arr;

    private FinalArray(T[] arr) {
        this.arr = Arrays.copyOf(arr, arr.length);
    }

    public static <T> FinalArray<T> from(T[] arr) {
        return new FinalArray<>(arr);
    }

    @SuppressWarnings("unused")
    public T get(int index) {
        return arr[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    @Override @NonNull
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < FinalArray.this.arr.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arr[current++];
            }
        };
    }
}