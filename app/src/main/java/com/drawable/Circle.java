package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.util.Color;

import java.math.BigDecimal;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Circle extends Drawable implements Shape {
    private double radius;

    public Circle(double x, double y, double radius, int color) {
        this(x, y, radius, color, 0, 0);
    }

    public Circle(double x, double y, double radius, Color color) {
        this(x, y, radius, color.getInt(), 0, 0);
    }

    public Circle(double x, double y, double radius, int color, double xVelocity, double yVelocity) {
        super(x, y, color, xVelocity, yVelocity);
        this.radius = radius;
    }

    public Circle(double x, double y, double radius, Color color, double xVelocity, double yVelocity) {
        this(x, y, radius, color.getInt(), xVelocity, yVelocity);
    }

    public Circle(Circle c) {
        super(c);
        this.radius = c.getRadius();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        double result = Math.PI * Math.pow(radius, 2);
        return Math.round(result * 100.0) / 100.0;
    }

    @Override
    public double perimeter() {
        double result = 2 * Math.PI * radius;
        return Math.round(result * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Circle)) return false;
        Circle other = (Circle) o;
        BigDecimal first = new BigDecimal(Double.toString(this.radius));
        BigDecimal second = new BigDecimal(Double.toString(other.radius));
        return first.compareTo(second) == 0 && this.getColor() == other.getColor();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());
        canvas.drawCircle((float) getX(), (float) getY(), (float) radius, paint);
        animate();
    }

    @Override
    public String toString() {
        return super.toString() + "\n \tRadius: " + getRadius() + "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) +
                "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n";
    }
}