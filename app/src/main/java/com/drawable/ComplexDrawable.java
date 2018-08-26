package com.drawable;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ComplexDrawable extends Drawable {
    private double x2;
    private double y2;

    public ComplexDrawable(double x1, double y1, double x2, double y2, int color) {
        super(x1, y1, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }
}