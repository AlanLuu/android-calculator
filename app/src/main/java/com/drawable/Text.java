package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Text implements Drawable {
    private String message;
    private double x;
    private double y;
    private int color;
    private double textSize;

    public Text(String message, double x, double y, double textSize, int color) {
        this.message = message;
        this.x = x;
        this.y = y;
        this.textSize = textSize;
        this.color = color;
    }

    public Text(Text text) {
        this(text.getMessage(), text.getX(), text.getY(), text.getTextSize(), text.getColor());
    }

    public String getMessage() {
        return message;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public double getTextSize() {
        return textSize;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setTextSize(double textSize) {
        this.textSize = textSize;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Text)) return false;
        Text other = (Text) o;
        return this.message.equals(other.message) && this.color == other.color;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize((float) textSize);
        paint.setColor(color);
        canvas.drawText(message, (float) x, (float) y, paint);
    }

    @Override
    public String toString() {
        return "Text: \n \tMessage: " + message + "\n \tX position: " + x + "\n \tY position: " +
                y + "\n \tColor: " + String.format("#%06X", (0xFFFFFF & color)) + "\n";
    }
}