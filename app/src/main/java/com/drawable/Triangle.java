package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.exception.IllegalShapeException;
import com.util.Color;

import java.util.Arrays;

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
        if (!(this instanceof RightTriangle)) {
            boolean triangleExists = sideLengths[0] + sideLengths[1] > sideLengths[2]
                    && sideLengths[0] + sideLengths[2] > sideLengths[1]
                    && sideLengths[1] + sideLengths[2] > sideLengths[0];
            if (!triangleExists) {
                throw new IllegalShapeException("Triangle does not satisfy the Triangle Inequality Theorem.");
            }
        }
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
        return Arrays.copyOf(sideLengths, sideLengths.length);
    }

    public double getBase() {
        return sideLengths[2];
    }

    public double getHeight() {
        return Math.round(area() / (0.5 * getBase()) * 100.0) / 100.0;
    }

    public void setSideLengths(double[] sideLengths) {
        if (!(this instanceof RightTriangle)) {
            boolean triangleExists = this.sideLengths[0] + this.sideLengths[1] > this.sideLengths[2]
                    && this.sideLengths[0] + this.sideLengths[2] > this.sideLengths[1]
                    && this.sideLengths[1] + this.sideLengths[2] > this.sideLengths[0];
            if (!triangleExists) {
                throw new IllegalShapeException("Triangle does not satisfy the Triangle Inequality Theorem.");
            }
        }
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
        double result = sideLengths[0] + sideLengths[1] + sideLengths[2];
        return Math.round(result * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Triangle)) return false;
        Triangle other = (Triangle) o;
        for (int i = 0; i < sideLengths.length; i++) {
            if (this.sideLengths[i] != other.sideLengths[i]) {
                return false;
            }
        }
        return true;
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

        float x = (float) (getX() + sideLengths[2] / 2);
        float y = (float) (getY() - sideLengths[0]);
        path.lineTo(x, y);
        x = (float) (x + sideLengths[2] / 2);
        y = (float) (y + sideLengths[0]);
        path.lineTo(x, y);
        x = (float) (x - sideLengths[2]);
        path.lineTo(x, y);
        path.close();

        canvas.drawPath(path, paint);
        animate();
    }

    @Override
    public String toString() {
        return super.toString() + "\n \tLength of side 1: " + sideLengths[0] + "\n \tLength of side 2: " +
                sideLengths[1] + "\n \tLength of base: " + sideLengths[2] + "\n \tLength of height: " + getHeight() +
                "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n";
    }
}