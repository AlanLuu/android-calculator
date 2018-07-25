package com.example.app.calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RandomCircles extends View {
    private List<Circle> circleArr = new ArrayList<>();
    private Paint paint = new Paint();
    private int width;
    private int height;

    public RandomCircles(Context context) {
        super(context);
        int numCircles = Utility.getRandomInt(10, 20);
        for (int i = 0; i < numCircles; i++) {
            circleArr.add(new Circle(0, 0, Utility.getRandomInt(30, 50), android.graphics.Color.parseColor(Color.getRandomColor())));
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        this.width = width;
        this.height = height;
        super.onSizeChanged(width, height, oldWidth, oldHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Circle circle : circleArr) {
            circle.setX(Utility.getRandomInt((int) circle.getRadius(), this.width - 1));
            circle.setY(Utility.getRandomInt((int) circle.getRadius(), this.height - 1));
            circle.draw(canvas, paint);
        }
    }
}