package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.util.Color;

@SuppressWarnings("unused")
public class RightTriangle extends Triangle {
    public RightTriangle(double x, double y, double[] sideLengths, int color) {
        super(x, y, sideLengths, color);
    }

    public RightTriangle(double x, double y, double[] sideLengths, Color color) {
        super(x, y, sideLengths, color);
    }

    public RightTriangle(double x, double y, double[] sideLengths, int color, double xVelocity, double yVelocity) {
        super(x, y, sideLengths, color, xVelocity, yVelocity);
    }

    public RightTriangle(double x, double y, double[] sideLengths, Color color, double xVelocity, double yVelocity) {
        super(x, y, sideLengths, color, xVelocity, yVelocity);
    }

    public RightTriangle(RightTriangle t) {
        super(t);
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

        float x = (float) getX();
        float y = (float) (getY() - getSideLengths()[0] / 2);
        path.lineTo(x, y);
        x = (float) (x + getSideLengths()[1]);
        y = (float) (y + getSideLengths()[0]);
        path.lineTo(x, y);
        x = (float) (x - getSideLengths()[1]);
        path.lineTo(x, y);
        path.close();

        canvas.drawPath(path, paint);
        animate();
    }

    @Override
    public String toString() {
        String s = this.getClass().getSimpleName() + ": \n \tX position: " + getX() + "\n \tY position: " + getY() +
                "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) + "\n \t" +
                "X velocity: " + getXVelocity() + "\n \tY velocity: " + getYVelocity();
        return s + "\n \tA: " + getSideLengths()[0] + "\n \tB: " + getSideLengths()[1] + "\n \tC: " + getSideLengths()[2]
                + "\n";
    }
}