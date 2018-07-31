package com.example.app.calculator;

import android.graphics.Canvas;
import android.graphics.Paint;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Shape {
    private int x;
    private int y;
    private int color;
    private int xVelocity = 0;
    private int yVelocity = 0;

    public Shape(int x, int y, int color) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Error: " + (x < 0 && y < 0 ? "x and y" : x < 0 ? "x" : "y") +
                    " cannot be negative.");
        }
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Shape(int x, int y, int color, int xVelocity, int yVelocity) {
        this(x, y, color);
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(int yVelocity) {
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
        return "This shape has not implemented its toString method yet.";
    }

    public abstract double area();
    public abstract double perimeter();
    public abstract boolean equals(Object o);
    public abstract void draw(Canvas canvas, Paint paint);
}