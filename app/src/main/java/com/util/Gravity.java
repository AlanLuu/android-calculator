package com.util;

import com.drawable.*;

@SuppressWarnings("unused")
public class Gravity {
    private Drawable[] drawables;
    private double gravity;
    private int canvasWidth;
    private int canvasHeight;

    public Gravity(Drawable drawable, double gravity) {
        this(new Drawable[]{drawable}, gravity);
    }

    public Gravity(Drawable[] drawables, double gravity) {
        this.drawables = new Drawable[drawables.length];
        System.arraycopy(drawables, 0, this.drawables, 0, drawables.length);
        this.gravity = gravity;
    }

    public Drawable[] getDrawables() {
        return drawables;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    private void manage(Circle c) {
        if (c.getY() + c.getRadius() > canvasHeight) {
            c.setY(canvasHeight - c.getRadius());
            c.setYVelocity(c.getYVelocity() * -0.6);
        }
        if (c.getY() - c.getRadius() < 0) {
            c.setY(c.getRadius());
            c.setYVelocity(c.getYVelocity() * -0.6);
        }
        if (c.getX() + c.getRadius() > canvasWidth) {
            c.setX(canvasWidth - c.getRadius());
            c.setXVelocity(c.getXVelocity() * -0.6);
        }
        if (c.getX() - c.getRadius() < 0) {
            c.setX(c.getRadius());
            c.setXVelocity(c.getXVelocity() * -0.6);
        }
    }

    private void manage(Rectangle r) {
        if (r.getY() + r.getHeight() > canvasHeight) {
            r.setY(canvasHeight - r.getHeight());
            r.setYVelocity(r.getYVelocity() * -0.6);
        }
        if (r.getY() < 0) {
            r.setY(-r.getY());
            r.setYVelocity(r.getYVelocity() * -0.6);
        }
        if (r.getX() + r.getWidth() > canvasWidth) {
            r.setX(canvasWidth - r.getWidth());
            r.setXVelocity(r.getXVelocity() * -0.6);
        }
        if (r.getX() < 0) {
            r.setX(-r.getX());
            r.setXVelocity(r.getXVelocity() * -0.6);
        }
    }

    private void manage(Text t) {
        if (t.getY() + t.getTextSize() > canvasHeight + (t.getTextSize() * 2)) {
            t.setY(canvasHeight - (t.getTextSize() * 2));
            t.setYVelocity(t.getYVelocity() * -0.6);
        }
        if (t.getY() < 0) {
            t.setY(t.getY() + (t.getTextSize() / 2));
            t.setYVelocity(t.getYVelocity() * -0.6);
        }
        if (t.getX() + t.getTextSize() > canvasWidth + (t.getTextSize() / 2) - (t.getText().length() * 16)) {
            t.setX(canvasWidth - (t.getTextSize() / 2) - (t.getText().length() * 16));
            t.setXVelocity(t.getXVelocity() * -0.6);
        }
        if (t.getX() < 0) {
            t.setX(t.getX() + (t.getTextSize() / 2));
            t.setXVelocity(t.getXVelocity() * -0.6);
        }
    }

    public void start(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        for (Drawable d : drawables) {
            d.setYVelocity(d.getYVelocity() + gravity);
            if (d instanceof Circle) {
                manage((Circle) d);
            } else if (d instanceof Rectangle) {
                manage((Rectangle) d);
            } else if (d instanceof Text) {
                manage((Text) d);
            }
        }
    }
}