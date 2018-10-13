package com.example.app.calculator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

import com.drawable.*
import com.util.Color
import com.util.Gravity

import com.util.Utility.getRandomInt

class GravitySim(context: Context) : View(context), Runnable {
    private val shapeArr: Array<Drawable> = arrayOf(
            Circle(100.0, 100.0, 40.0, Color.parseColor("#33c446"), 2.0, 0.0),
            Circle(200.0, 100.0, 40.0, Color.RED, 2.0, 0.0),
            Square(300.0, 100.0, 50, Color.MAGENTA), RightTriangle(100.0, 100.0,
            doubleArrayOf(75.0, 75.0), Color.parseColor("#0675d1"), 2.0, 0.0)
    )
    private val paint = Paint()
    private var start = System.currentTimeMillis()
    private var distance = 0f
    private var count = 0
    private val gravityManager: Gravity

    init {
        gravityManager = Gravity(shapeArr, 0.1)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        val x = e.x
        val y = e.y
        val start: Long
        val stop: Long

        if (e.action == MotionEvent.ACTION_DOWN || e.action == MotionEvent.ACTION_MOVE) {
            start = System.currentTimeMillis()
            if (this.count < 1) this.start = start
            for (d in shapeArr) {
                d.x = x.toDouble()
                d.y = y.toDouble()
                if (d is ComplexDrawable) {
                    d.x2 = d.x + (d.x2 - d.x)
                    d.y2 = d.y + (d.y2 - d.y)
                }
            }
            if (e.action == MotionEvent.ACTION_MOVE) {
                this.distance += 1000f
            }
            this.count++
        }
        if (e.action == MotionEvent.ACTION_UP) {
            stop = System.currentTimeMillis()
            val timeElapsed = stop - this.start + 1000
            var temp = getRandomInt(1, 10)
            for (d in shapeArr) {
                if (temp > 5) {
                    d.xVelocity = (this.distance / timeElapsed).toDouble()
                    temp = getRandomInt(1, 5)
                } else {
                    d.xVelocity = (-(this.distance / timeElapsed)).toDouble()
                    temp = getRandomInt(6, 10)
                }
                d.yVelocity = (if (getRandomInt(1, 10) > 5) this.distance / timeElapsed else -(this.distance / timeElapsed)).toDouble()
            }
            this.distance = 0f
            this.count = 0
        }
        performClick()
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (d in shapeArr) {
            d.draw(canvas, paint)
            gravityManager.start(width, height)
        }
    }

    override fun run() {
        while (true) {
            postInvalidate()
            try {
                Thread.sleep((1000 / 60).toLong())
            } catch (e: InterruptedException) {}
        }
    }
}