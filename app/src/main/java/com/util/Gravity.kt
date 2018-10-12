package com.util

import com.drawable.*

@Suppress("unused", "MemberVisibilityCanBePrivate")
class Gravity {
    var drawables: FinalArray<Drawable>? = null
        private set
    var gravity: Double = 0.0
    private var canvasWidth: Int = 0
    private var canvasHeight: Int = 0

    constructor(drawable: Drawable, gravity: Double) : this(arrayOf<Drawable>(drawable), gravity)

    constructor(drawables: Array<Drawable>, gravity: Double) {
        this.drawables = FinalArray.from(drawables)
        this.gravity = gravity
    }

    constructor(drawables: List<Drawable>, gravity: Double) {
        this.drawables = FinalArray.from(drawables.toTypedArray())
        this.gravity = gravity
    }

    private fun manage(c: Circle) {
        if (c.y + c.radius > canvasHeight) {
            c.y = canvasHeight - c.radius
            c.yVelocity = c.yVelocity * -0.6
        }
        if (c.y - c.radius < 0) {
            c.y = c.radius
            c.yVelocity = c.yVelocity * -0.6
        }
        if (c.x + c.radius > canvasWidth) {
            c.x = canvasWidth - c.radius
            c.xVelocity = c.xVelocity * -0.6
        }
        if (c.x - c.radius < 0) {
            c.x = c.radius
            c.xVelocity = c.xVelocity * -0.6
        }
    }

    private fun manage(r: Rectangle) {
        if (r.y + r.height > canvasHeight) {
            r.y = canvasHeight - r.height.toDouble()
            r.yVelocity = r.yVelocity * -0.6
        }
        if (r.y < 0) {
            r.y = -r.y
            r.yVelocity = r.yVelocity * -0.6
        }
        if (r.x + r.width > canvasWidth) {
            r.x = canvasWidth - r.width.toDouble()
            r.xVelocity = r.xVelocity * -0.6
        }
        if (r.x < 0) {
            r.x = -r.x
            r.xVelocity = r.xVelocity * -0.6
        }
    }

    private fun manage(t: Text) {
        if (t.y + t.textSize > canvasHeight + t.textSize * 2) {
            t.y = canvasHeight - t.textSize * 2
            t.yVelocity = t.yVelocity * -0.6
        }
        if (t.y < 0) {
            t.y = t.y + t.textSize / 2
            t.yVelocity = t.yVelocity * -0.6
        }
        if (t.x + t.textSize > canvasWidth + t.textSize / 2 - t.text.length * 16) {
            t.x = canvasWidth.toDouble() - t.textSize / 2 - (t.text.length * 16).toDouble()
            t.xVelocity = t.xVelocity * -0.6
        }
        if (t.x < 0) {
            t.x = t.x + t.textSize / 2
            t.xVelocity = t.xVelocity * -0.6
        }
    }

    private fun manage(t: Triangle) {
        if (t.y > canvasHeight) {
            t.y = canvasHeight.toDouble()
            t.yVelocity = t.yVelocity * -0.6
        }
        if (t.y - t.height < 0) {
            t.y = t.height
            t.yVelocity = t.yVelocity * -0.6
        }
        if (t.x + t.getSideLengths()[2] > canvasWidth) {
            t.x = canvasWidth - t.getSideLengths()[2]
            t.xVelocity = t.xVelocity * -0.6
        }
        if (t.x < 0) {
            t.x = 0.0
            t.xVelocity = t.xVelocity * -0.6
        }
    }

    private fun manage(t: RightTriangle) {
        if (t.y > canvasHeight) {
            t.y = canvasHeight.toDouble()
            t.yVelocity = t.yVelocity * -0.6
        }
        if (t.y - t.height < 0) {
            t.y = t.height
            t.yVelocity = t.yVelocity * -0.6
        }
        if (t.x + t.getSideLengths()[1] > canvasWidth) {
            t.x = canvasWidth - t.getSideLengths()[1]
            t.xVelocity = t.xVelocity * -0.6
        }
        if (t.x < 0) {
            t.x = 0.0
            t.xVelocity = t.xVelocity * -0.6
        }
    }

    private fun manage(l: Line) {
        if (l.y > canvasHeight) {
            l.y = canvasHeight.toDouble()
            l.yVelocity = l.yVelocity * -0.6
        }
        if (l.y2 > canvasHeight) {
            l.y2 = canvasHeight.toDouble()
            l.yVelocity = l.yVelocity * -0.6
        }
        if (l.y < 0) {
            l.y = -l.y
            l.yVelocity = l.yVelocity * -0.6
        }
        if (l.y2 < 0) {
            l.y2 = -l.y2
            l.yVelocity = l.yVelocity * -0.6
        }
        if (l.x2 > canvasWidth) {
            l.x2 = canvasWidth.toDouble()
            l.xVelocity = l.xVelocity * -0.6
        }
        if (l.x < 0) {
            l.x = -l.x
            l.xVelocity = l.xVelocity * -0.6
        }
    }

    fun start(canvasWidth: Int, canvasHeight: Int) {
        this.canvasWidth = canvasWidth
        this.canvasHeight = canvasHeight

        for (d in drawables!!) {
            d.yVelocity += gravity

            //Compiler automatically does a downcast here
            if (d is Circle) {
                manage(d)
            } else if (d is Rectangle) {
                manage(d)
            } else if (d is Text) {
                manage(d)
            } else if (d is Triangle && d !is RightTriangle) {
                manage(d)
            } else if (d is RightTriangle) {
                manage(d)
            } else if (d is Line) {
                manage(d)
            }

        }
    }
}