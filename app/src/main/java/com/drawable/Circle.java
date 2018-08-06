package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.math.BigDecimal;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Circle extends Shape {
    private float radius;

    public Circle(double x, double y, float radius, int color) {
        super(x, y, color);
        this.radius = radius;
    }

    public Circle(double x, double y, float radius, int color, double xVelocity, double yVelocity) {
        super(x, y, color, xVelocity, yVelocity);
        this.radius = radius;
    }

    public Circle(Circle c) {
        super(c.getX(), c.getY(), c.getColor(), c.getXVelocity(), c.getYVelocity());
        this.radius = c.getRadius();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Circle)) return false;
        Circle other = (Circle) o;
        BigDecimal first = new BigDecimal(Float.toString(this.radius));
        BigDecimal second = new BigDecimal(Float.toString(other.radius));
        return first.equals(second) && this.getColor() == other.getColor();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());
        canvas.drawCircle((float) getX(), (float) getY(), radius, paint);
    }

    @Override
    public String toString() {
        return "Circle: \n \tX position: " + getX() + "\n \tY position: " + getY() + "\n \tRadius: " +
                getRadius() + "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) +
                "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n";
    }
}