package com.example.app.calculator;

@SuppressWarnings("unused")
public enum Color {
    RED(android.graphics.Color.RED),
    ORANGE(android.graphics.Color.parseColor("#ffa500")),
    YELLOW(android.graphics.Color.parseColor("#ffff00")),
    GREEN(android.graphics.Color.GREEN),
    BLUE(android.graphics.Color.BLUE),
    VIOLET(android.graphics.Color.parseColor("#ee82ee")),
    PURPLE(android.graphics.Color.parseColor("#800080")),
    MAGENTA(android.graphics.Color.parseColor("#ff00ff")),
    CYAN(android.graphics.Color.parseColor("#00ffff")),
    WHITE(android.graphics.Color.WHITE),
    BLACK(android.graphics.Color.BLACK),
    LIGHT_BLUE(android.graphics.Color.parseColor("#33ccff")),
    DEFAULT();

    private int color;

    Color(int color) {
        this.color = color;
    }

    Color() {
        this(android.graphics.Color.BLACK);
    }

    int toInt() {
        return color;
    }

    static String getRandomColor() {
        char[] characters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder color = new StringBuilder();
        color.append('#');
        for (int i = 0; i < 6; i++) {
            color.append(characters[(int) (Math.random() * characters.length)]);
        }
        return color.toString();
    }
}