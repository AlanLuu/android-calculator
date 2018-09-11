package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.util.Color;

import java.math.BigDecimal;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Line extends ComplexDrawable {
    private double lineSize;

    public Line(double x1, double y1, double x2, double y2, double lineSize, int color) {
        this(x1, y1, x2, y2, lineSize, color, 0, 0);
    }

    public Line(double x1, double y1, double x2, double y2, double lineSize, Color color) {
        this(x1, y1, x2, y2, lineSize, color.getInt(), 0, 0);
    }

    public Line(double x1, double y1, double x2, double y2, double lineSize, int color, double xVelocity, double yVelocity) {
        super(x1, y1, x2, y2, color, xVelocity, yVelocity);
        this.lineSize = lineSize;
    }

    public Line(double x1, double y1, double x2, double y2, double lineSize, Color color, double xVelocity, double yVelocity) {
        this(x1, y1, x2, y2, lineSize, color.getInt(), xVelocity, yVelocity);
    }

    public Line(Line line) {
        super(line);
        this.lineSize = line.getLineSize();
    }

    public double getLineSize() {
        return lineSize;
    }

    public void setLineSize(double lineSize) {
        this.lineSize = lineSize;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Line)) return false;
        Line other = (Line) o;
        BigDecimal first = new BigDecimal(Double.toString(this.lineSize));
        BigDecimal second = new BigDecimal(Double.toString(other.lineSize));
        return first.compareTo(second) == 0 && this.getColor() == other.getColor();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());
        paint.setStrokeWidth((float) lineSize);
        canvas.drawLine((float) getX(), (float) getY(), (float) getX2(), (float) getY2(), paint);
        animate();
    }
}