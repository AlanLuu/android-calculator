package com.example.app.calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.*;

public class RandomCircles extends View {
    private List<Shape> circleArr = new ArrayList<Shape>();
    private Paint paint = new Paint();
    private Random r = new Random();

    public RandomCircles(Context context) {
        super(context);
        for (int i = 0; i < 10; i++) {
            int randomRadius = getRandomInt(30, 50);
            int randomX = r.nextInt(480 - randomRadius) + randomRadius;
            int randomY = r.nextInt(880 - randomRadius) + randomRadius;
            Shape circle = new Circle(randomX, randomY, randomRadius, android.graphics.Color.parseColor(Color.getRandomColor()));
            circle.setColor(android.graphics.Color.parseColor(Color.getRandomColor()));
            circleArr.add(circle);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Shape shape : circleArr) {
            shape.draw(canvas, paint);
        }
    }

    private int getRandomInt(int min, int max) {
        if (min >= max || max <= min) {
            throw new IllegalArgumentException();
        }
        return r.nextInt(max - min + 1) + min;
    }
}