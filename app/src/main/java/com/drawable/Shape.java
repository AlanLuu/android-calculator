package com.drawable;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Shape implements Drawable {
    private double x;
    private double y;
    private int color;
    private double xVelocity = 0;
    private double yVelocity = 0;

    public Shape(double x, double y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Shape(double x, double y, int color, double xVelocity, double yVelocity) {
        this(x, y, color);
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updatePos(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " has not implemented its toString method yet.";
    }

    public abstract double area();
    public abstract double perimeter();
}