package com.drawable

import android.graphics.Canvas
import android.graphics.Paint

import com.util.Color

import java.math.BigDecimal

@Suppress("unused", "NAME_SHADOWING", "EqualsOrHashCode")
class Line : ComplexDrawable {
    var lineSize: Double = 0.toDouble()

    constructor(x1: Double, y1: Double, x2: Double, y2: Double, lineSize: Double, color: Color) :
            this(x1, y1, x2, y2, lineSize, color.int, 0.0, 0.0)

    constructor(x1: Double, y1: Double, x2: Double, y2: Double, lineSize: Double, color: Int, xVelocity: Double = 0.0,
                              yVelocity: Double = 0.0) : super(x1, y1, x2, y2, color, xVelocity, yVelocity) {
        this.lineSize = lineSize
    }

    constructor(x1: Double, y1: Double, x2: Double, y2: Double, lineSize: Double, color: Color, xVelocity: Double, yVelocity: Double) :
            this(x1, y1, x2, y2, lineSize, color.int, xVelocity, yVelocity)

    constructor(line: Line) : super(line) {
        this.lineSize = line.lineSize
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Line) return false
        val other = other as Line?
        val first = BigDecimal(java.lang.Double.toString(this.lineSize))
        val second = BigDecimal(java.lang.Double.toString(other!!.lineSize))
        return first.compareTo(second) == 0 && this.color == other.color
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.FILL
        paint.color = color
        paint.strokeWidth = lineSize.toFloat()
        canvas.drawLine(x.toFloat(), y.toFloat(), x2.toFloat(), y2.toFloat(), paint)
        animate()
    }

    override fun toString(): String {
        return super.toString() + "\n \tLine size: " + lineSize + "\n"
    }
}