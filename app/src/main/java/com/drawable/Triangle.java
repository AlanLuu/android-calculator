package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.util.Color;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Triangle extends Drawable implements Shape {
    private double[] sideLengths;

    public Triangle(double x, double y, double[] sideLengths, int color) {
        this(x, y, sideLengths, color, 0, 0);
    }

    public Triangle(double x, double y, double[] sideLengths, Color color) {
        this(x, y, sideLengths, color.getInt(), 0, 0);
    }

    public Triangle(double x, double y, double[] sideLengths, int color, double xVelocity, double yVelocity) {
        super(x, y, color, xVelocity, yVelocity);
        this.sideLengths = sideLengths;
    }

    public Triangle(double x, double y, double[] sideLengths, Color color, double xVelocity, double yVelocity) {
        this(x, y, sideLengths, color.getInt(), xVelocity, yVelocity);
    }

    public Triangle(Triangle t) {
        super(t);
        this.sideLengths = t.getSideLengths();
    }

    public double[] getSideLengths() {
        return sideLengths;
    }

    public void setSideLengths(double[] sideLengths) {
        this.sideLengths = sideLengths;
    }

    @Override
    public double area() {
        double a = sideLengths[0], b = sideLengths[1], c = sideLengths[2];
        double s = (a + b + c) / 2;
        double result = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        return Math.round(result * 100.0) / 100.0;
    }

    @Override
    public double perimeter() {
        return sideLengths[0] + sideLengths[1] + sideLengths[2];
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo((float) getX(), (float) getY());

        float x = (float) (getX() + sideLengths[1] / 2);
        float y = (float) (getY() - sideLengths[0]);
        path.lineTo(x, y);
        x = (float) (x + sideLengths[1] / 2);
        y = (float) (y + sideLengths[0]);
        path.lineTo(x, y);
        x = (float) (x - sideLengths[1]);
        path.lineTo(x, y);
        path.close();

        canvas.drawPath(path, paint);
        animate();
    }

    @Override
    public String toString() {
        return super.toString() + "\n \tSide Length 1: " + sideLengths[0] + "\n \tSide Length 2: " +
                sideLengths[1] + "\n \tSide Length 3: " + sideLengths[2] + "\n";
    }
}