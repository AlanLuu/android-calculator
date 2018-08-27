package com.drawable;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Square extends Rectangle {
    public Square(double x, double y, int sideLength, int color) {
        this(x, y, sideLength, color, 0, 0);
    }

    public Square(double x, double y, int sideLength, int color, double xVelocity, double yVelocity) {
        super(x, y, sideLength, sideLength, color, xVelocity, yVelocity);
    }

    public Square(Square s) {
        super(s);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Square)) return false;
        Square other = (Square) o;
        return this.getColor() == other.getColor();
    }
}