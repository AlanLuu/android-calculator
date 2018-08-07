package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Line implements Drawable {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private int color;

    public Line(double x1, double y1, double x2, double y2, int color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public int getColor() {
        return color;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawLine((float) x1, (float) y1, (float) x2, (float) y2, paint);
    }

    @Override
    public String toString() {
        return "Line: \n \tx1: " + x1 + "\n \ty1: " + y1 + "\n \tx2: " + x2 + "\n \ty2: " + y2 +
                "\n \tColor: " + String.format("#%06X", (0xFFFFFF & color)) + "\n";
    }
}