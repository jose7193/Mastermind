package ControlFolder;

import java.awt.*;

/**
 * Created by AlexL on 5/24/14.
 */
public enum Pegs {
    NO_VALUE(null, -1),
    BLUE(Color.BLUE, 0),
    RED(Color.RED, 1),
    BLACK(Color.BLACK, 2),
    WHITE(Color.WHITE, 3),
    GREEN(Color.GREEN, 4),
    ORANGE(Color.ORANGE, 5),
    PINK(Color.PINK, 6),
    GREY(Color.GRAY, 7);

    private final Color color;
    private final int intValue;

    private Pegs(Color color, int intValue) {
        this.color = color;
        this.intValue = intValue;
    }

    public Color getColor() {
        return color;
    }

    public int getIntValue() {
        return intValue;
    }

    public static Color convertIntToColor(int i) {
        if (i == BLUE.getIntValue()) {
            return BLUE.getColor();
        } else if (i == RED.getIntValue()) {
            return RED.getColor();
        } else if (i == BLACK.getIntValue()) {
            return BLACK.getColor();
        } else if (i == WHITE.getIntValue()) {
            return WHITE.getColor();
        } else if (i == GREEN.getIntValue()) {
            return GREEN.getColor();
        } else if (i == ORANGE.getIntValue()) {
            return ORANGE.getColor();
        } else if (i == PINK.getIntValue()) {
            return PINK.getColor();
        } else if (i == GREY.getIntValue()) {
            return GREY.getColor();
        } else {
            return null;
        }
    }

    public static int convertColorToInt(Color c) {
        if (c == BLUE.getColor()) {
            return BLUE.getIntValue();
        } else if (c == RED.getColor()) {
            return RED.getIntValue();
        } else if (c == BLACK.getColor()) {
            return BLACK.getIntValue();
        } else if (c == WHITE.getColor()) {
            return WHITE.getIntValue();
        } else if (c == GREEN.getColor()) {
            return GREEN.getIntValue();
        } else if (c == ORANGE.getColor()) {
            return ORANGE.getIntValue();
        } else if (c == PINK.getColor()) {
            return PINK.getIntValue();
        } else if (c == GREY.getColor()) {
            return GREY.getIntValue();
        } else {
            return NO_VALUE.getIntValue();
        }
    }


}
