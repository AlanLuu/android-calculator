package com.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.util.Color;

import java.math.BigDecimal;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Text extends Drawable {
    private String text;
    private double textSize;

    public Text(String text, double x, double y, double textSize, int color) {
        super(x, y, color);
        this.text = text;
        this.textSize = textSize;
    }

    public Text(String text, double x, double y, double textSize, Color color) {
        this(text, x, y, textSize, color.getInt());
    }

    public Text(Text text) {
        super(text);
        this.text = text.getText();
        this.textSize = text.getTextSize();
    }

    public String getText() {
        return text;
    }

    public double getTextSize() {
        return textSize;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextSize(double textSize) {
        this.textSize = textSize;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Text)) return false;
        Text other = (Text) o;
        BigDecimal first = new BigDecimal(Double.toString(this.textSize));
        BigDecimal second = new BigDecimal(Double.toString(other.textSize));
        return this.text.equals(other.text) && first.compareTo(second) == 0 && this.getColor() == other.getColor();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize((float) textSize);
        paint.setColor(getColor());
        canvas.drawText(text, (float) getX(), (float) getY(), paint);
        animate();
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "Text: \n \tMessage: " + text + "\n \tText size: " + textSize + "\n" +
                s.substring(this.getClass().getSimpleName().length() + 3, s.indexOf("X velocity: "));
    }
}