package com.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

import com.util.Color

@Suppress("unused")
class RightTriangle : Triangle {

    override val base: Double
        get() = getSideLengths()[1]

    override val height: Double
        get() = getSideLengths()[0]

    private val hypotenuse: Double
        get() = pythagorean(getSideLengths()[0], getSideLengths()[1])

    constructor(x: Double, y: Double, sideLengths: DoubleArray, color: Int) : super(x, y, sideLengths, color)

    constructor(x: Double, y: Double, sideLengths: DoubleArray, color: Color) : super(x, y, sideLengths, color)

    constructor(x: Double, y: Double, sideLengths: DoubleArray, color: Int, xVelocity: Double, yVelocity: Double) :
            super(x, y, sideLengths, color, xVelocity, yVelocity)

    constructor(x: Double, y: Double, sideLengths: DoubleArray, color: Color, xVelocity: Double, yVelocity: Double) :
            super(x, y, sideLengths, color, xVelocity, yVelocity)

    constructor(t: RightTriangle) : super(t)

    override fun area(): Double {
        val a = getSideLengths()[0]
        val b = getSideLengths()[1]
        val c = hypotenuse
        val s = (a + b + c) / 2
        val result = Math.sqrt(s * (s - a) * (s - b) * (s - c))
        return Math.round(result * 100.0) / 100.0
    }

    override fun perimeter(): Double {
        val result = getSideLengths()[0] + getSideLengths()[1] + hypotenuse
        return Math.round(result * 100.0) / 100.0
    }

    private fun pythagorean(a: Double, b: Double): Double {
        return Math.sqrt(a * a + b * b)
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.FILL
        paint.color = color
        paint.strokeWidth = 4f
        paint.isAntiAlias = true

        val path = Path()
        path.fillType = Path.FillType.EVEN_ODD
        path.moveTo(x.toFloat(), y.toFloat())

        var x = x.toFloat()
        var y = (y - getSideLengths()[0]).toFloat()
        path.lineTo(x, y)
        x = (x + getSideLengths()[1]).toFloat()
        y = (y + getSideLengths()[0]).toFloat()
        path.lineTo(x, y)
        x = (x - getSideLengths()[1]).toFloat()
        path.lineTo(x, y)
        path.close()

        canvas.drawPath(path, paint)
        animate()
    }

    override fun toString(): String {
        val s = this.javaClass.simpleName + ": \n \tX position: " + x + "\n \tY position: " + y +
                "\n \tColor: " + colorAsHex + "\n \t" + "X velocity: " + xVelocity + "\n \tY velocity: " + yVelocity
        val a = getSideLengths()[0]
        val b = getSideLengths()[1]
        return s + "\n \tLength of side A: " + a + "\n \tLength of side B: " + b + "\n \tLength of hypotenuse: " +
                Math.round(pythagorean(a, b) * 100.0) / 100.0 + "\n \tArea: " + area() + "\n \tPerimeter: " +
                perimeter() + "\n"
    }
}