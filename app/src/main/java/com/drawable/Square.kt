package com.drawable

import com.util.Color

@Suppress("unused", "NAME_SHADOWING", "EqualsOrHashCode")
class Square : Rectangle {

    constructor(x: Double, y: Double, sideLength: Int, color: Color) : this(x, y, sideLength, color.int, 0.0, 0.0)

    @JvmOverloads constructor(x: Double, y: Double, sideLength: Int, color: Int, xVelocity: Double = 0.0, yVelocity: Double = 0.0) :
            super(x, y, sideLength, sideLength, color, xVelocity, yVelocity)

    constructor(x: Double, y: Double, sideLength: Int, color: Color, xVelocity: Double, yVelocity: Double) :
            this(x, y, sideLength, color.int, xVelocity, yVelocity)

    constructor(s: Square) : super(s)

    override fun equals(other: Any?): Boolean {
        if (other !is Square) return false
        val other = other as Square?
        return this.color == other!!.color
    }
}