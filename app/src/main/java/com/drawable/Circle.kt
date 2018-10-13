package com.drawable

import android.graphics.Canvas
import android.graphics.Paint

import com.util.Color

import java.math.BigDecimal

@Suppress("unused", "NAME_SHADOWING", "EqualsOrHashCode")
class Circle : Drawable, Shape {
    var radius: Double = 0.toDouble()

    constructor(x: Double, y: Double, radius: Double, color: Color) : this(x, y, radius, color.int, 0.0, 0.0)

    constructor(x: Double, y: Double, radius: Double, color: Int, xVelocity: Double = 0.0, yVelocity: Double = 0.0) :
            super(x, y, color, xVelocity, yVelocity) {
        this.radius = radius
    }

    constructor(x: Double, y: Double, radius: Double, color: Color, xVelocity: Double, yVelocity: Double) :
            this(x, y, radius, color.int, xVelocity, yVelocity)

    constructor(c: Circle) : super(c) {
        this.radius = c.radius
    }

    override fun area(): Double {
        val result = Math.PI * Math.pow(radius, 2.0)
        return Math.round(result * 100.0) / 100.0
    }

    //This is actually the circumference.
    override fun perimeter(): Double {
        val result = 2.0 * Math.PI * radius
        return Math.round(result * 100.0) / 100.0
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Circle) return false
        val other = other as Circle?
        val first = BigDecimal(java.lang.Double.toString(this.radius))
        val second = BigDecimal(java.lang.Double.toString(other!!.radius))
        return first.compareTo(second) == 0 && this.color == other.color
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.FILL
        paint.color = color
        canvas.drawCircle(x.toFloat(), y.toFloat(), radius.toFloat(), paint)
        animate()
    }

    override fun toString(): String {
        return super.toString() + "\n \tRadius: " + radius + "\n \tArea: " + area() +
                "\n \tCircumference: " + perimeter() + "\n"
    }
}