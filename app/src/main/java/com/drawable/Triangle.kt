package com.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

import com.exception.IllegalShapeException
import com.util.Color

import java.util.Arrays

@Suppress("unused", "NAME_SHADOWING", "EqualsOrHashCode")
open class Triangle : Drawable, Shape {
    private var sideLengths: DoubleArray? = null

    open val base: Double
        get() = sideLengths!![2]

    open val height: Double
        get() = Math.round(area() / (0.5 * base) * 100.0) / 100.0

    constructor(x: Double, y: Double, sideLengths: DoubleArray, color: Color) :
            this(x, y, sideLengths, color.int, 0.0, 0.0)

    @JvmOverloads constructor(x: Double, y: Double, sideLengths: DoubleArray, color: Int, xVelocity: Double = 0.0, yVelocity: Double = 0.0) :
            super(x, y, color, xVelocity, yVelocity) {
        if (this !is RightTriangle) {
            val triangleExists = (sideLengths[0] + sideLengths[1] > sideLengths[2]
                    && sideLengths[0] + sideLengths[2] > sideLengths[1]
                    && sideLengths[1] + sideLengths[2] > sideLengths[0])
            if (!triangleExists) {
                throw IllegalShapeException("Triangle does not satisfy the Triangle Inequality Theorem.")
            }
        }
        this.sideLengths = sideLengths
    }

    constructor(x: Double, y: Double, sideLengths: DoubleArray, color: Color, xVelocity: Double, yVelocity: Double) : this(x, y, sideLengths, color.int, xVelocity, yVelocity) {}

    constructor(t: Triangle) : super(t) {
        this.sideLengths = t.getSideLengths()
    }

    fun getSideLengths(): DoubleArray {
        return Arrays.copyOf(sideLengths!!, sideLengths!!.size)
    }

    fun setSideLengths(sideLengths: DoubleArray) {
        if (this !is RightTriangle) {
            val triangleExists = (this.sideLengths!![0] + this.sideLengths!![1] > this.sideLengths!![2]
                    && this.sideLengths!![0] + this.sideLengths!![2] > this.sideLengths!![1]
                    && this.sideLengths!![1] + this.sideLengths!![2] > this.sideLengths!![0])
            if (!triangleExists) {
                throw IllegalShapeException("Triangle does not satisfy the Triangle Inequality Theorem.")
            }
        }
        this.sideLengths = sideLengths
    }

    override fun area(): Double {
        val a = sideLengths!![0]
        val b = sideLengths!![1]
        val c = sideLengths!![2]
        val s = (a + b + c) / 2
        val result = Math.sqrt(s * (s - a) * (s - b) * (s - c))
        return Math.round(result * 100.0) / 100.0
    }

    override fun perimeter(): Double {
        val result = sideLengths!![0] + sideLengths!![1] + sideLengths!![2]
        return Math.round(result * 100.0) / 100.0
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Triangle) return false
        val other = other as Triangle?
        for (i in sideLengths!!.indices) {
            if (this.sideLengths!![i] != other!!.sideLengths!![i]) {
                return false
            }
        }
        return true
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.FILL
        paint.color = color
        paint.strokeWidth = 4f
        paint.isAntiAlias = true

        val path = Path()
        path.fillType = Path.FillType.EVEN_ODD
        path.moveTo(x.toFloat(), y.toFloat())

        var x = (x + sideLengths!![2] / 2).toFloat()
        var y = (y - sideLengths!![0]).toFloat()
        path.lineTo(x, y)
        x = (x + sideLengths!![2] / 2).toFloat()
        y = (y + sideLengths!![0]).toFloat()
        path.lineTo(x, y)
        x = (x - sideLengths!![2]).toFloat()
        path.lineTo(x, y)
        path.close()

        canvas.drawPath(path, paint)
        animate()
    }

    override fun toString(): String {
        return super.toString() + "\n \tLength of side 1: " + sideLengths!![0] + "\n \tLength of side 2: " +
                sideLengths!![1] + "\n \tLength of base: " + sideLengths!![2] + "\n \tLength of height: " + height +
                "\n \tArea: " + area() + "\n \tPerimeter: " + perimeter() + "\n"
    }
}