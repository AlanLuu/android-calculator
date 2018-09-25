package com.example.app.calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.drawable.*;

import java.util.ArrayList;
import java.util.List;

import static com.util.Color.*;
import static com.util.Utility.getRandomInt;

public class RandomCircles extends View implements Runnable {
    private List<Circle> circles = new ArrayList<>();
    private Paint paint = new Paint();
    private Text[] moreCirclesText = {
            new Text("Tap the screen to add", 0, 50, 35, BLUE),
            new Text("more circles!", 0, 100, 35, BLUE)
    };
    private int width;
    private int height;
    private int speedRange = 5;

    public static final int STARTING_CIRCLES = 10;

    public RandomCircles(Context context) {
        super(context);
        for (int i = 0; i < STARTING_CIRCLES; i++) {
            circles.add(randomCircle(0, 0, speedRange));
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        this.width = width;
        this.height = height;
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        for (Circle circle : circles) {
            circle.setX(getRandomInt((int) circle.getRadius(), this.width - 60));
            circle.setY(getRandomInt((int) circle.getRadius(), this.height - 60));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                circles.add(randomCircle(x, y, speedRange));
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
        for (Circle circle : circles) {
            circle.draw(canvas, paint);
            if (circle.getX() + circle.getRadius() > width || circle.getX() - circle.getRadius() < 0) {
                circle.setXVelocity(-circle.getXVelocity());
            }
            if (circle.getY() + circle.getRadius() > height || circle.getY() - circle.getRadius() < 0) {
                circle.setYVelocity(-circle.getYVelocity());
            }
        }
        if (moreCirclesText != null) {
            for (Text text : moreCirclesText) {
                text.draw(canvas, paint);
            }
            if (circles.size() > STARTING_CIRCLES + 10) moreCirclesText = null;
        }
    }

    private Circle randomCircle(double x, double y, int range) {
        int randXVel;
        int randYVel;
        do {
            randXVel = getRandomInt(-range, range);
        } while (randXVel > -range / 2 && randXVel < range / 2);

        do {
            randYVel = getRandomInt(-range, range);
        } while (randYVel > -range / 2 && randYVel < range / 2);

        return new Circle(x, y, getRandomInt(30, 50), getRandomColor(), randXVel, randYVel);
    }

    @SuppressWarnings({"InfiniteLoopStatement", "EmptyCatchBlock"})
    @Override
    public void run() {
        for (;;) {
            postInvalidate();
            try {
                Thread.sleep(1000 / 60); //60fps
            } catch (InterruptedException e) {}
        }
    }
}