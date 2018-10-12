package com.drawable

import android.graphics.Canvas
import android.graphics.Paint

import com.util.Color

import java.math.BigDecimal

@Suppress("unused", "NAME_SHADOWING", "EqualsOrHashCode")
class Text : Drawable {
    var text: String = ""
    var textSize: Double = 0.toDouble()

    constructor(text: String, x: Double, y: Double, textSize: Double, color: Int) : super(x, y, color) {
        this.text = text
        this.textSize = textSize
    }

    constructor(text: String, x: Double, y: Double, textSize: Double, color: Color) : this(text, x, y, textSize, color.int)

    constructor(text: Text) : super(text) {
        this.text = text.text
        this.textSize = text.textSize
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Text) return false
        val other = other as Text?
        val first = BigDecimal(java.lang.Double.toString(this.textSize))
        val second = BigDecimal(java.lang.Double.toString(other!!.textSize))
        return this.text == other.text && first.compareTo(second) == 0 && this.color == other.color
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.FILL
        paint.textSize = textSize.toFloat()
        paint.color = color
        canvas.drawText(text, x.toFloat(), y.toFloat(), paint)
        animate()
    }

    override fun toString(): String {
        val s = super.toString()
        return "Text: \n \tMessage: " + text + "\n \tText size: " + textSize + "\n" +
                s.substring(this.javaClass.simpleName.length + 3, s.indexOf("X velocity: "))
    }
}