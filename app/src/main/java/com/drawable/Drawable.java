package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Drawable {
    private double x;
    private double y;
    private int color;
    private double xVelocity;
    private double yVelocity;

    public Drawable(double x, double y, int color, double xVelocity, double yVelocity) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public Drawable(double x, double y, int color) {
        this(x, y, color, 0, 0);
    }

    public Drawable(Drawable d) {
        this(d.getX(), d.getY(), d.getColor(), d.getXVelocity(), d.getYVelocity());
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

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void updatePos(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    protected void animate() {
        if (getX() == 0 && getY() == 0) return;
        setX(getX() + getXVelocity());
        setY(getY() + getYVelocity());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": \n \tX position: " + x + "\n \tY position: " + y +
                "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) + "\n \t" +
                "X velocity: " + xVelocity + "\n \tY velocity: " + yVelocity;
    }

    public abstract void draw(Canvas canvas, Paint paint);
}