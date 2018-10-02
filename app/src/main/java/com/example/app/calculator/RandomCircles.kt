package com.example.app.calculator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

import com.drawable.*
import com.util.Color

import java.util.ArrayList

import com.util.Utility.getRandomInt

class RandomCircles(context: Context) : View(context), Runnable {
    private val circles = ArrayList<Circle>()
    private val paint = Paint()
    private var moreCirclesText: Array<Text> = arrayOf(
            Text("Tap the screen to add", 0.0, 50.0, 35.0, Color.BLUE),
            Text("more circles!", 0.0, 100.0, 35.0, Color.BLUE)
    )
    private val speedRange = 5

    init {
        for (i in 0 until STARTING_CIRCLES) {
            circles.add(Circle(0.0, 0.0, getRandomInt(30, 50).toDouble(), Color.values()[i]))
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        for (circle in circles) {
            circle.x = getRandomInt(circle.radius.toInt(), width - 60).toDouble()
            circle.y = getRandomInt(circle.radius.toInt(), height - 60).toDouble()
            do {
                circle.xVelocity = getRandomInt(-speedRange, speedRange).toDouble()
            } while (circle.xVelocity > -speedRange / 2 && circle.xVelocity < speedRange / 2)

            do {
                circle.yVelocity = getRandomInt(-speedRange, speedRange).toDouble()
            } while (circle.yVelocity > -speedRange / 2 && circle.yVelocity < speedRange / 2)
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        val x = e.x
        val y = e.y

        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                circles.add(randomCircle(x.toDouble(), y.toDouble(), speedRange))
            }
        }
        performClick()
        return super.onTouchEvent(e)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (circle in circles) {
            circle.draw(canvas, paint)
            if (circle.x + circle.radius > width || circle.x - circle.radius < 0) {
                circle.xVelocity = -circle.xVelocity
            }
            if (circle.y + circle.radius > height || circle.y - circle.radius < 0) {
                circle.yVelocity = -circle.yVelocity
            }
        }
        if (circles.size <= STARTING_CIRCLES + 10) {
            for (text in moreCirclesText) {
                text.draw(canvas, paint)
            }
        }
    }

    private fun randomCircle(x: Double, y: Double, range: Int): Circle {
        var randXVel: Int
        var randYVel: Int
        do {
            randXVel = getRandomInt(-range, range)
        } while (randXVel > -range / 2 && randXVel < range / 2)

        do {
            randYVel = getRandomInt(-range, range)
        } while (randYVel > -range / 2 && randYVel < range / 2)

        return Circle(x, y, getRandomInt(30, 50).toDouble(), Color.getRandomColor(), randXVel.toDouble(), randYVel.toDouble())
    }

    override fun run() {
        while (true) {
            postInvalidate()
            try {
                Thread.sleep((1000 / 60).toLong())
            } catch (e: InterruptedException) {}
        }
    }

    companion object {
        val STARTING_CIRCLES = Color.valueOf("CYAN").ordinal + 1
    }
}