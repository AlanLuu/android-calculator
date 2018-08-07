package com.drawable;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Square extends Rectangle implements Drawable {
    private int sideLength;

    public Square(double x, double y, int sideLength, int color) {
        super(x, y, sideLength, sideLength, color);
    }

    public Square(double x, double y, int sideLength, int color, double xVelocity, double yVelocity) {
        super(x, y, sideLength, sideLength, color, xVelocity, yVelocity);
    }

    public Square(Square s) {
        super(s);
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Square)) return false;
        Square other = (Square) o;
        return this.getColor() == other.getColor();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}