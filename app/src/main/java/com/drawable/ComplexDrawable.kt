package com.drawable

import com.util.Color

@Suppress("unused")
abstract class ComplexDrawable : Drawable {
    var x2: Double = 0.toDouble()
    var y2: Double = 0.toDouble()

    constructor(x1: Double, y1: Double, x2: Double, y2: Double, color: Color) : this(x1, y1, x2, y2, color.int, 0.0, 0.0)

    constructor(x1: Double, y1: Double, x2: Double, y2: Double, color: Int, xVelocity: Double = 0.0, yVelocity: Double = 0.0) :
            super(x1, y1, color, xVelocity, yVelocity) {
        this.x2 = x2
        this.y2 = y2
    }

    constructor(x1: Double, y1: Double, x2: Double, y2: Double, color: Color, xVelocity: Double, yVelocity: Double) :
            this(x1, y1, x2, y2, color.int, xVelocity, yVelocity)

    constructor(c: ComplexDrawable) : super(c) {
        this.x2 = c.x2
        this.y2 = c.y2
    }

    override fun animate() {
        super.animate()
        x2 += xVelocity
        y2 += yVelocity
    }

    override fun toString(): String {
        return super.toString() + "\n \tx2: " + x2 + "\n \ty2: " + y2
    }
}