package com.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

import com.util.Color

@Suppress("unused", "NAME_SHADOWING", "EqualsOrHashCode")
open class Rectangle : Drawable, Shape {
    var width: Int = 0
    var height: Int = 0

    private var sideLength: Int
        get() = (this as? Square)?.width ?: 0
        set(sideLength) {
            if (this !is Square) return
            width = sideLength
            height = sideLength
        }

    constructor(x: Double, y: Double, width: Int, height: Int, color: Color) : this(x, y, width, height, color.int, 0.0, 0.0)

    constructor(x: Double, y: Double, width: Int, height: Int, color: Int, xVelocity: Double = 0.0, yVelocity: Double = 0.0) :
            super(x, y, color, xVelocity, yVelocity) {
        this.width = width
        this.height = height
    }

    constructor(x: Double, y: Double, width: Int, height: Int, color: Color, xVelocity: Double, yVelocity: Double) :
            this(x, y, width, height, color.int, xVelocity, yVelocity)

    constructor(r: Rectangle) : super(r) {
        this.width = r.width
        this.height = r.height
    }

    override fun area(): Double {
        return (width * height).toDouble()
    }

    override fun perimeter(): Double {
        return (width * 2 + height * 2).toDouble()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Rectangle) return false
        val other = other as Rectangle?
        return this.width == other!!.width && this.height == other.height && this.color == other.color
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.FILL
        paint.color = color

        val rect = RectF(x.toFloat(), y.toFloat(), (x + width).toFloat(), (y + height).toFloat())
        canvas.drawRect(rect, paint)
        animate()
    }

    override fun toString(): String {
        return if (this is Square) {
            super.toString() + "\n \tSide length: " +
                    sideLength + "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n"
        } else super.toString() + "\n \tWidth: " + width + "\n \tHeight: " + height +
                "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n"
    }
}