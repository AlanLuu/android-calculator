package com.example.app.calculator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height, int color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public Rectangle(int x, int y, int width, int height, int color, int xVelocity, int yVelocity) {
        super(x, y, color, xVelocity, yVelocity);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return (width * 2) + (height * 2);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rectangle)) return false;
        Rectangle other = (Rectangle) o;
        return this.width == other.width && this.height == other.height && this.getColor() == other.getColor();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());

        Rect rect = new Rect(getX(), getY(), getX() + width, getY() + height);
        canvas.drawRect(rect, paint);
    }

    @Override
    public String toString() {
        if (this instanceof Square) {
            return "Square: \n \tX position: " + getX() + "\n \tY position: " + getY() + "\n \tSide length: " +
                    ((Square) this).getSideLength() + "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) +
                    "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n";
        }
        return "Rectangle: \n \tX position: " + getX() + "\n \tY position: " + getY() + "\n \tWidth: " +
                width + "\n \tHeight: "+ height + "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) +
                "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n";
    }
}