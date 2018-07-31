package com.example.app.calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class SquarePattern extends View implements Runnable {
    private Square[][] squareArr = new Square[17][9];
    private Paint paint = new Paint();
    private int width;
    private int height;
    private int yVelocity = 5;

    public SquarePattern(Context context) {
        super(context);

        for (int i = 0; i < squareArr.length; i++) {
            for (int j = 0; j < squareArr[i].length; j++) {
                int color = Color.parseColor("#00beff");
                if (j % 2 != 0) color = Color.parseColor("#ffa0c3");
                squareArr[i][j] = new Square(j * 120, i * 110, 80, color, 0, yVelocity);
            }
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        this.width = width;
        this.height = height;
        super.onSizeChanged(width, height, oldWidth, oldHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        yVelocity = -yVelocity;
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
        for (Square[] s : squareArr) {
            for (Square square : s) {
                square.setY(square.getY() + yVelocity);
                if (yVelocity > 0 && square.getY() >= height + 800) {
                    square.setY(0);
                }
                if (yVelocity < 0 && square.getY() <= -800) {
                    square.setY(height);
                }
                square.draw(canvas, paint);
            }
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