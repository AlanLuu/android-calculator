package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Text extends Drawable {
    private String text;
    private double textSize;

    public Text(String text, double x, double y, double textSize, int color) {
        super(x, y, color);
        this.text = text;
        this.textSize = textSize;
    }

    public Text(Text text) {
        this(text.getText(), text.getX(), text.getY(), text.getTextSize(), text.getColor());
    }

    public String getText() {
        return text;
    }

    public double getTextSize() {
        return textSize;
    }

    public void setMessage(String message) {
        this.text = message;
    }

    public void setText(double textSize) {
        this.textSize = textSize;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Text)) return false;
        Text other = (Text) o;
        return this.text.equals(other.text) && this.getColor() == other.getColor();
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize((float) textSize);
        paint.setColor(getColor());
        canvas.drawText(text, (float) getX(), (float) getY(), paint);
    }

    @Override
    public String toString() {
        return "Text: \n \tMessage: " + text + "\n \tX position: " + getX() + "\n \tY position: " +
                getY() + "\n \tColor: " + String.format("#%06X", (0xFFFFFF & getColor())) + "\n";
    }
}