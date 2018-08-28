package com.example.app.calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.drawable.*;
import com.util.Color;
import com.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class RandomCircles extends View implements Runnable {
    private List<Circle> circles = new ArrayList<>();
    private Paint paint = new Paint();
    private Text[] moreCirclesText = {
            new Text("Tap the screen to add", 0, 50, 35, Color.BLUE.toInt()),
            new Text("more circles!", 0, 100, 35, Color.BLUE.toInt())
    };
    private int width;
    private int height;
    private int speedRange = 5;
    private int numCircles = 10;

    public RandomCircles(Context context) {
        super(context);
        for (int i = 0; i < numCircles; i++) {
            randomCircle(0, 0, speedRange);
        }
    }

    @Override
    protected void onSizeChanged(final int width, int height, int oldWidth, int oldHeight) {
        this.width = width;
        this.height = height;
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        for (Circle circle : circles) {
            circle.setX(Utility.getRandomInt((int) circle.getRadius(), this.width - 60));
            circle.setY(Utility.getRandomInt((int) circle.getRadius(), this.height - 60));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                randomCircle(x, y, speedRange);
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
            if (circles.size() > numCircles + 10) moreCirclesText = null;
        }
    }

    private void randomCircle(float x, float y, int range) {
        int randColor = Color.parseColor(Color.getRandomColor());
        int randXVel;
        int randYVel;
        do {
            randXVel = Utility.getRandomInt(-range, range);
        } while (randXVel > -range / 2 && randXVel < range / 2);

        do {
            randYVel = Utility.getRandomInt(-range, range);
        } while (randYVel > -range / 2 && randYVel < range / 2);

        circles.add(new Circle(x, y, Utility.getRandomInt(30, 50), randColor, randXVel, randYVel));
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