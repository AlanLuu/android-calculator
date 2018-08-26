package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Line extends ComplexDrawable {
    private double lineSize;

    public Line(double x1, double y1, double x2, double y2, double lineSize, int color) {
        super(x1, y1, x2, y2, color);
        this.lineSize = lineSize;
    }

    public Line(Line line) {
        this(line.getX(), line.getY(), line.getX2(), line.getY2(), line.getLineSize(), line.getColor());
    }

    public double getLineSize() {
        return lineSize;
    }

    public void setLineSize(double lineSize) {
        this.lineSize = lineSize;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());
        paint.setStrokeWidth((float) lineSize);
        canvas.drawLine((float) getX(), (float) getY(), (float) getX2(), (float) getY2(), paint);
    }

    @Override
    public String toString() {
        return "Line: \n \tx1: " + getX() + "\n \ty1: " + getY() + "\n \tx2: " + getX2() + "\n \ty2: " + getY2() +
                "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) + "\n";
    }
}