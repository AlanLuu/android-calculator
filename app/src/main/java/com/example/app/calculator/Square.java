package com.example.app.calculator;

public class Square extends Rectangle {
    private int sideLength;

    public Square(int x, int y, int sideLength, int color) {
        super(x, y, sideLength, sideLength, color);
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}