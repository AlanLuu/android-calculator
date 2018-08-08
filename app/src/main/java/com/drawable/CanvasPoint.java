package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.NonNull;

import com.util.Color;

import java.util.Arrays;

@SuppressWarnings({"unused", "WeakerAccess"})
public class CanvasPoint extends Point implements Drawable, Comparable<CanvasPoint> {
    private int color;
    private double[] points;

    public CanvasPoint(int x, int y, int color) {
        super(x, y);
        this.color = color;
    }

    public CanvasPoint() {
        this(0, 0, Color.parseColor("#000000"));
    }

    public CanvasPoint(int color) {
        this(0, 0, color);
    }

    public CanvasPoint(double[] points) {
        this.points = points;
    }

    public CanvasPoint(CanvasPoint point) {
        this(point.x, point.y, point.getColor());
    }

    public int getColor() {
        return color;
    }

    public double[] getPoints() {
        return points;
    }

    public String getPointsAsStr() {
        if (points == null) return null;
        return Arrays.toString(points);
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CanvasPoint)) return false;
        CanvasPoint other = (CanvasPoint) o;
        return super.equals(x, y) && this.color == other.color;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        if (points == null) {
            canvas.drawPoint(x, y, paint);
        } else {
            float[] pointsAsFloat = new float[points.length];
            for (int i = 0; i < points.length; i++) {
                pointsAsFloat[i] = (float) points[i];
            }
            canvas.drawPoints(pointsAsFloat, paint);
        }
    }

    @Override
    public int compareTo(@NonNull CanvasPoint other) {
        int x = (other.x - this.x) * (other.x - this.x);
        int y = (other.y - this.y) * (other.y - this.y);
        return (int) (other.x > this.x || other.y > this.y ? Math.sqrt(x + y) : -Math.sqrt(x + y));
    }

    @Override
    public String toString() {
        return points != null ? "Points: " + Arrays.toString(points) : "Point: \n \tX position: " +
                x + "\n \tY position: " + y + "\n \tColor: " + String.format("#%06X", (0xFFFFFF & color)) + "\n";
    }
}