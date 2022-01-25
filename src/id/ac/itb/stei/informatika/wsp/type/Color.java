package id.ac.itb.stei.informatika.wsp.type;

import java.util.Random;

/**
 * {@code Color} is a set of predefined colors used
 * in {@code ColoredMatrix} to output puzzle solution.
 * @see id.ac.itb.stei.informatika.wsp.type.ColoredMatrix
 */
public enum Color {
    /**
     * Uncolored
     */
    PLAIN,
    /**
     * Bright green color (color code 10)
     */
    GREEN,
    /**
     * Bright red color (color code 9)
     */
    RED,
    /**
     * Light blue color (color code 45)
     */
    BLUE,
    /**
     * Magenta color (color code 13)
     */
    MAGENTA,
    /**
     * Cyan color (color code 14)
     */
    CYAN,
    /**
     * Lime color (color code 48)
     */
    LIME,
    /**
     * Slightly bright brown color (color code 94)
     */
    BROWN,
    /**
     * Purple color (color code 93)
     */
    PURPLE,
    /**
     * Bright orange color (color code 202)
     */
    ORANGE;

    private static final Color[] colors = {
            Color.GREEN,
            Color.RED,
            Color.BLUE,
            Color.MAGENTA,
            Color.CYAN,
            Color.LIME,
            Color.BROWN,
            Color.PURPLE,
            Color.ORANGE
    };

    private static final Random randomizer = new Random();

    /**
     * Picks a random color from a predefined colors.
     * @return a random color.
     */
    public static Color random() {
        int index = Color.randomizer.nextInt(Color.colors.length);
        return Color.colors[index];
    }

    /**
     * Wraps a string with the color.
     * @param filler string to wrap.
     * @return a printable colored string.
     */
    public String wrap(String filler) {
        if (this == Color.PLAIN) {
            return filler;
        }

        String result = "";

        int code = switch (this) {
            case GREEN -> 10;
            case RED -> 9;
            case BLUE -> 45;
            case MAGENTA -> 13;
            case CYAN -> 14;
            case LIME -> 48;
            case BROWN -> 94;
            case PURPLE -> 93;
            case ORANGE -> 202;
            default -> -1;
        };

        result += "\033[1;38;5;" + code + "m";
        result += filler;
        result += "\033[0m";

        return result;
    }
}
