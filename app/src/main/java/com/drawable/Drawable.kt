package com.drawable

import android.graphics.Canvas
import android.graphics.Paint

import com.util.Color

@Suppress("unused", "NAME_SHADOWING")
abstract class Drawable
@JvmOverloads constructor(var x: Double, var y: Double, var color: Int, var xVelocity: Double = 0.0, var yVelocity: Double = 0.0) {

    val colorAsHex: String
        get() = String.format("#%06X", 0xFFFFFF and color)

    constructor(x: Double, y: Double, color: Color, xVelocity: Double, yVelocity: Double) : this(x, y, color.int, xVelocity, yVelocity)

    constructor(x: Double, y: Double, color: Color) : this(x, y, color.int, 0.0, 0.0)

    constructor(d: Drawable) : this(d.x, d.y, d.color, d.xVelocity, d.yVelocity)

    fun setColor(color: Color) {
        this.color = color.int
    }

    fun setColor(color: String) {
        var color = color
        if (color.substring(0, 1) != "#") {
            color = "#$color"
        }
        this.color = Color.parseColor(color)
    }

    fun setPos(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    fun updatePos(dx: Double, dy: Double) {
        this.x += dx
        this.y += dy
    }

    protected open fun animate() {
        x += xVelocity
        y += yVelocity
    }

    override fun toString(): String {
        return this.javaClass.simpleName + ": \n \tX position: " + x + "\n \tY position: " + y +
                "\n \tColor: " + colorAsHex + "\n \t" + "X velocity: " + xVelocity + "\n \tY velocity: " + yVelocity
    }

    abstract fun draw(canvas: Canvas, paint: Paint)
}