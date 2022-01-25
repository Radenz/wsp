package id.ac.itb.stei.informatika.wsp.type;

/**
 * {@code Point} represents an ordered pair of integer values.
 */
public class Point {
    private int x;
    private int y;

    /**
     * Creates a new {@code Point} with a specified pair of
     * integer values.
     * @param x first integer value.
     * @param y second integer value.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * Compares a {@code Point} object with another {@code Point}
     * object.
     * @param point another {@code Point} object to compare with.
     * @return true if the objects hold the same integer values in order.
     */
    public boolean equals(Point point) {
        return this.x == point.x() && this.y == point.y();
    }

    /**
     * Returns the first integer value.
     * @return the first integer value.
     */
    public int x() {
        return this.x;
    }

    /**
     * Returns the second integer value.
     * @return the second integer value.
     */
    public int y() {
        return this.y;
    }
}
