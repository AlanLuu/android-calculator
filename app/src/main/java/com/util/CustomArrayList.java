package com.util;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"unused", "unchecked", "WeakerAccess"})
public class CustomArrayList<E> implements Iterable<E> {
    private int size = 0;
    private Object[] data;

    public CustomArrayList() {
        data = new Object[10];
    }

    public CustomArrayList(int initialCapacity) {
        data = new Object[initialCapacity];
    }

    public boolean add(E e) {
        if (size == data.length) {
            expand();
        }
        data[size++] = e;
        return true;
    }

    public void add(int index, E e) {
        if (size == data.length) {
            expand();
        }
        System.arraycopy(data, index, data, index + 1,
                size - index);
        data[index] = e;
        size++;
    }

    public E remove(int index) {
        check(index);

        Object oldValue = data[index];
        fastRemove(index);

        return (E) oldValue;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if ((o == null && data[i] == null) || (o != null && o.equals(data[i]))) {
                fastRemove(i);
                return true;
            }
        }
        return false;
    }

    public E get(int index) {
        check(index);
        return (E) data[index];
    }

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if ((o == null && data[i] == null) || (o != null && o.equals(data[i]))) {
                return i;
            }
        }
        return -1;
    }

    private void expand() {
        data = Arrays.copyOf(data, data.length * 2);
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null;
    }

    private void check(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) builder.append(", ");
        }
        builder.append("]");
        return builder.toString();
    }

    @Override @NonNull
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < CustomArrayList.this.size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) data[current++];
            }
        };
    }
}