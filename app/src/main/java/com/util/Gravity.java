package com.util;

import com.drawable.*;

import java.util.List;

@SuppressWarnings("unused")
public final class Gravity {
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

    public Gravity(List<? extends Drawable> drawables, double gravity) {
        this.drawables = new Drawable[drawables.size()];
        for (int i = 0; i < drawables.size(); i++) {
            this.drawables[i] = drawables.get(i);
        }
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

    private void manage(Triangle t) {
        if (t.getY() > canvasHeight) {
            t.setY(canvasHeight);
            t.setYVelocity(t.getYVelocity() * -0.6);
        }
        if (t.getY() - t.getHeight() < 0) {
            t.setY(t.getHeight());
            t.setYVelocity(t.getYVelocity() * -0.6);
        }
        if (t.getX() + t.getSideLengths()[2] > canvasWidth) {
            t.setX(canvasWidth - t.getSideLengths()[2]);
            t.setXVelocity(t.getXVelocity() * -0.6);
        }
        if (t.getX() < 0) {
            t.setX(0);
            t.setXVelocity(t.getXVelocity() * -0.6);
        }
    }

    private void manage(RightTriangle t) {
        if (t.getY() > canvasHeight) {
            t.setY(canvasHeight);
            t.setYVelocity(t.getYVelocity() * -0.6);
        }
        if (t.getY() - t.getHeight() < 0) {
            t.setY(t.getHeight());
            t.setYVelocity(t.getYVelocity() * -0.6);
        }
        if (t.getX() + t.getSideLengths()[1] > canvasWidth) {
            t.setX(canvasWidth - t.getSideLengths()[1]);
            t.setXVelocity(t.getXVelocity() * -0.6);
        }
        if (t.getX() < 0) {
            t.setX(0);
            t.setXVelocity(t.getXVelocity() * -0.6);
        }
    }

    private void manage(Line l) {
        if (l.getY() > canvasHeight) {
            l.setY(canvasHeight);
            l.setYVelocity(l.getYVelocity() * -0.6);
        }
        if (l.getY2() > canvasHeight) {
            l.setY2(canvasHeight);
            l.setYVelocity(l.getYVelocity() * -0.6);
        }
        if (l.getY() < 0) {
            l.setY(-l.getY());
            l.setYVelocity(l.getYVelocity() * -0.6);
        }
        if (l.getY2() < 0) {
            l.setY2(-l.getY2());
            l.setYVelocity(l.getYVelocity() * -0.6);
        }
        if (l.getX2() > canvasWidth) {
            l.setX2(canvasWidth);
            l.setXVelocity(l.getXVelocity() * -0.6);
        }
        if (l.getX() < 0) {
            l.setX(-l.getX());
            l.setXVelocity(l.getXVelocity() * -0.6);
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
            } else if (d instanceof Triangle && !(d instanceof RightTriangle)) {
                manage((Triangle) d);
            } else if (d instanceof RightTriangle) {
                manage((RightTriangle) d);
            } else if (d instanceof Line) {
                manage((Line) d);
            }
        }
    }
}