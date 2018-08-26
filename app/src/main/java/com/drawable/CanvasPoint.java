package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.util.Color;

import java.util.Arrays;

@SuppressWarnings({"unused", "WeakerAccess"})
public class CanvasPoint extends Drawable {
    private double[] points;

    public CanvasPoint(int x, int y, int color) {
        super(x, y, color);
    }

    public CanvasPoint() {
        this(0, 0, Color.parseColor("#000000"));
    }

    public CanvasPoint(int color) {
        this(0, 0, color);
    }

    public CanvasPoint(double[] points) {
        this();
        this.points = points;
    }

    public CanvasPoint(CanvasPoint point) {
        this((int) point.getX(), (int) point.getY(), point.getColor());
    }

    public double[] getPoints() {
        return points;
    }

    public String getPointsAsStr() {
        if (points == null) return null;
        return Arrays.toString(points);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CanvasPoint)) return false;
        CanvasPoint other = (CanvasPoint) o;
        return this.getX() == other.getX() && this.getY() ==other.getY() && this.getColor() == other.getColor();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());
        if (points == null) {
            canvas.drawPoint((float) getX(), (float) getY(), paint);
        } else {
            float[] pointsAsFloat = new float[points.length];
            for (int i = 0; i < points.length; i++) {
                pointsAsFloat[i] = (float) points[i];
            }
            canvas.drawPoints(pointsAsFloat, paint);
        }
    }

    @Override
    public String toString() {
        return points != null ? "Points: " + Arrays.toString(points) : "Point: \n \tX position: " +
                getX() + "\n \tY position: " + getY() + "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) + "\n";
    }
}