package com.drawable;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Shape extends Drawable {
    public Shape(double x, double y, int color) {
        super(x, y, color, 0 ,0);
    }

    public Shape(double x, double y, int color, double xVelocity, double yVelocity) {
        super(x, y, color, xVelocity, yVelocity);
    }

    public abstract double area();
    public abstract double perimeter();
}