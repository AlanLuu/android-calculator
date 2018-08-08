package com.example.app.calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.drawable.*;
import com.util.Color;
import com.util.Utility;

public class GravitySim extends View implements Runnable {
    private Shape[] shapeArr = {
            new Circle(100, 100, 40, Color.parseColor("#000000"), 2, 0),
    };
    private Paint paint = new Paint();
    private int width;
    private int height;
    private long start = System.currentTimeMillis();
    private float distance = 0;
    private int count = 0;

    public GravitySim(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        this.width = width;
        this.height = height;
        super.onSizeChanged(width, height, oldWidth, oldHeight);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        long start;
        long stop;

        for (Shape shape : shapeArr) {
            if (e.getAction() == MotionEvent.ACTION_DOWN || e.getAction() == MotionEvent.ACTION_MOVE) {
                start = System.currentTimeMillis();
                if (this.count < 1) {
                    this.start = start;
                }
                shape.setX(x);
                shape.setY(y);
                if (e.getAction() == MotionEvent.ACTION_MOVE) {
                    this.distance += 1000;
                }
                this.count++;
            }
            if (e.getAction() == MotionEvent.ACTION_UP) {
                stop = System.currentTimeMillis();
                long timeElapsed = stop - this.start + 1000;
                shape.setXVelocity(Utility.getRandomInt(1, 10) > 5 ? this.distance / timeElapsed : -(this.distance / timeElapsed));
                shape.setYVelocity(Utility.getRandomInt(1, 10) > 5 ? this.distance / timeElapsed : -(this.distance / timeElapsed));
                this.distance = 0;
                this.count = 0;
            }
        }
        performClick();
        return true;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        double gravity = 0.2;
        super.onDraw(canvas);
        for (Shape shape : shapeArr) {
            shape.setX(shape.getX() + shape.getXVelocity());
            shape.setY(shape.getY() + shape.getYVelocity());
            shape.setYVelocity(shape.getYVelocity() + gravity);
            if (shape instanceof Circle && shape.getY() + ((Circle) shape).getRadius() > height) {
                shape.setY(height - ((Circle) shape).getRadius());
                shape.setYVelocity(shape.getYVelocity() * -0.6);
            }
            if (shape instanceof Circle && shape.getY() - ((Circle) shape).getRadius() < 0) {
                shape.setY(((Circle) shape).getRadius());
                shape.setYVelocity(shape.getYVelocity() * -0.6);
            }
            if (shape instanceof Circle && shape.getX() + ((Circle) shape).getRadius() > width) {
                shape.setX(width - ((Circle) shape).getRadius());
                shape.setXVelocity(shape.getXVelocity() * -0.6);
            }
            if (shape instanceof Circle && shape.getX() - ((Circle) shape).getRadius() < 0) {
                shape.setX(((Circle) shape).getRadius());
                shape.setXVelocity(shape.getXVelocity() * -0.6);
            }
            shape.draw(canvas, paint);
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        for (;;) {
            postInvalidate();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread was interrupted");
            }
        }
    }
}