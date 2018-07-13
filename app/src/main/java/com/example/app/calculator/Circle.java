package com.example.app.calculator;

import android.graphics.Canvas;
import android.graphics.Paint;

@SuppressWarnings("WeakerAccess")
public class Circle extends Shape {
    private float radius;

    public Circle(int x, int y, float radius, int color) {
        super(x, y, color);
        this.radius = radius;
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

    public boolean equals(Object o) {
        if (!(o instanceof Circle)) return false;
        Circle other = (Circle) o;
        return this.radius == other.radius && this.getColor() == other.getColor();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());
        canvas.drawCircle(getX(), getY(), radius, paint);
    }

    @Override
    public String toString() {
        return "Circle: \n \tX position: " + getX() + "\n \tY position: " + getY() + "\n \tRadius: " +
                getRadius() + "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) +
                "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n";
    }
}