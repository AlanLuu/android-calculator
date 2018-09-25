package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.util.Color;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Rectangle extends Drawable implements Shape {
    private int width;
    private int height;

    public Rectangle(double x, double y, int width, int height, int color) {
        this(x, y, width, height, color, 0, 0);
    }

    public Rectangle(double x, double y, int width, int height, Color color) {
        this(x, y, width, height, color.getInt(), 0, 0);
    }

    public Rectangle(double x, double y, int width, int height, int color, double xVelocity, double yVelocity) {
        super(x, y, color, xVelocity, yVelocity);
        this.width = width;
        this.height = height;
    }

    public Rectangle(double x, double y, int width, int height, Color color, double xVelocity, double yVelocity) {
        this(x, y, width, height, color.getInt(), xVelocity, yVelocity);
    }

    public Rectangle(Rectangle r) {
        super(r);
        this.width = r.getWidth();
        this.height = r.getHeight();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSideLength() {
        if (!(this instanceof Square)) return 0;
        return width;
    }

    public void setSideLength(int sideLength) {
        if (!(this instanceof Square)) return;
        width = sideLength;
        height = sideLength;
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

        RectF rect = new RectF((float) getX(), (float) getY(), (float) (getX() + width), (float) (getY() + height));
        canvas.drawRect(rect, paint);
        animate();
    }

    @Override
    public String toString() {
        if (this instanceof Square) {
            return super.toString() + "\n \tSide length: " +
                    getSideLength() + "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n";
        }
        return super.toString() + "\n \tWidth: " + width + "\n \tHeight: "+ height + "\n \tColor: " +
                getColorAsHex() + "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n";
    }
}