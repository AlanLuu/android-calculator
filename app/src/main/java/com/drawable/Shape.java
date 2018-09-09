package com.drawable;

@SuppressWarnings("WeakerAccess")
public abstract class Shape extends Drawable {
    public Shape(double x, double y, int color, double xVelocity, double yVelocity) {
        super(x, y, color, xVelocity, yVelocity);
    }

    public Shape(Shape s) {
        super(s);
    }

    public abstract double area();
    public abstract double perimeter();
}