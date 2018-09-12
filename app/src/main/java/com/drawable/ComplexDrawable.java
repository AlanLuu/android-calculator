package com.drawable;

import com.util.Color;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ComplexDrawable extends Drawable {
    private double x2;
    private double y2;

    public ComplexDrawable(double x1, double y1, double x2, double y2, int color) {
        this(x1, y1, x2, y2, color, 0, 0);
    }

    public ComplexDrawable(double x1, double y1, double x2, double y2, Color color) {
        this(x1, y1, x2, y2, color.getInt(), 0, 0);
    }

    public ComplexDrawable(double x1, double y1, double x2, double y2, int color, double xVelocity, double yVelocity) {
        super(x1, y1, color, xVelocity, yVelocity);
        this.x2 = x2;
        this.y2 = y2;
    }

    public ComplexDrawable(double x1, double y1, double x2, double y2, Color color, double xVelocity, double yVelocity) {
        this(x1, y1, x2, y2, color.getInt(), xVelocity, yVelocity);
    }

    public ComplexDrawable(ComplexDrawable c) {
        super(c);
        this.x2 = c.getX2();
        this.y2 = c.getY2();
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

    @Override
    protected void animate() {
        super.animate();
        setX2(getX2() + getXVelocity());
        setY2(getY2() + getYVelocity());
    }

    @Override
    public String toString() {
        return super.toString() + "\n \tx2: " + getX2() + "\n \ty2: " + getY2();
    }
}