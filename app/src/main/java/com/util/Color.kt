package com.util

@Suppress("NAME_SHADOWING")
enum class Color constructor(val int: Int) {
    RED(android.graphics.Color.RED),
    ORANGE(android.graphics.Color.parseColor("#ffa500")),
    YELLOW(android.graphics.Color.parseColor("#ffff00")),
    GREEN(android.graphics.Color.GREEN),
    BLUE(android.graphics.Color.parseColor("#0000ff")),
    VIOLET(android.graphics.Color.parseColor("#ee82ee")),
    PURPLE(android.graphics.Color.parseColor("#800080")),
    MAGENTA(android.graphics.Color.parseColor("#ff00ff")),
    CYAN(android.graphics.Color.parseColor("#00ffff")),
    WHITE(android.graphics.Color.WHITE),
    BLACK(android.graphics.Color.BLACK),
    LIGHT_BLUE(android.graphics.Color.parseColor("#33ccff"));

    companion object {
        fun parseColor(hex: String): Int {
            var hex = hex
            if (hex.substring(0, 1) != "#") {
                hex = "#$hex"
            }
            return android.graphics.Color.parseColor(hex)
        }

        val randomColor: Int
            get() = parseColor(randomHex)

        private val randomHex: String
            get() {
                val characters = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
                val color = StringBuilder()
                        .append("#")
                for (i in 0..5) {
                    color.append(characters[(Math.random() * characters.size).toInt()])
                }
                return color.toString()
            }
    }
}