package com.example.app.calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RandomCircles extends View implements Runnable {
    private List<Circle> circleArr = new ArrayList<>();
    private Paint paint = new Paint();
    private int width;
    private int height;
    private int speedRange = 5;

    public RandomCircles(Context context) {
        super(context);

        int numCircles = 10;
        for (int i = 0; i < numCircles; i++) {
            int randColor = android.graphics.Color.parseColor(Color.getRandomColor());
            int randXVel;
            int randYVel;
            do {
                randXVel = Utility.getRandomInt(-speedRange, speedRange);
            } while (randXVel > -speedRange / 2 && randXVel < speedRange / 2);

            do {
                randYVel = Utility.getRandomInt(-speedRange, speedRange);
            } while (randYVel > -speedRange / 2 && randYVel < speedRange / 2);

            circleArr.add(new Circle(0, 0, Utility.getRandomInt(30, 50), randColor, randXVel, randYVel));
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        this.width = width;
        this.height = height;
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        for (Circle circle : circleArr) {
            circle.setX(Utility.getRandomInt((int) circle.getRadius(), this.width - 40));
            circle.setY(Utility.getRandomInt((int) circle.getRadius(), this.height - 40));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                circleArr.add(new Circle((int) x, (int) y, Utility.getRandomInt(30, 50),
                        android.graphics.Color.parseColor(Color.getRandomColor()),
                        Utility.getRandomInt(-speedRange, speedRange), Utility.getRandomInt(-speedRange, speedRange)));
                break;
        }
        performClick();
        return super.onTouchEvent(e);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Circle circle : circleArr) {
            circle.setX(circle.getX() + circle.getXVelocity());
            circle.setY(circle.getY() + circle.getYVelocity());
            if (circle.getX() + circle.getRadius() > width || circle.getX() - circle.getRadius() < 0) {
                circle.setXVelocity(-circle.getXVelocity());
            }
            if (circle.getY() + circle.getRadius() > height || circle.getY() - circle.getRadius() < 0) {
                circle.setYVelocity(-circle.getYVelocity());
            }
            circle.draw(canvas, paint);
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        for (;;) {
            postInvalidate();
            try {
                Thread.sleep(1000 / 60); //60fps
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread was interrupted");
            }
        }
    }
}