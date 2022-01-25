package id.ac.itb.stei.informatika.wsp.type;

/**
 * {@code Direction} is a set of 8 equidistant discrete
 * directions.
 */
public enum Direction {
    /**
     * Left direction.
     */
    LEFTWARDS,
    /**
     * Right direction.
     */
    RIGHTWARDS,
    /**
     * Down direction.
     */
    DOWNWARDS,
    /**
     * Up direction.
     */
    UPWARDS,
    /**
     * Left down direction.
     */
    LEFT_DOWNWARDS,
    /**
     * Right down direction.
     */
    RIGHT_DOWNWARDS,
    /**
     * Left up direction.
     */
    LEFT_UPWARDS,
    /**
     * Right up direction.
     */
    RIGHT_UPWARDS;

    /**
     * Checks if the direction is horizontally leftwards,
     * defined as leftwards is its horizontal projection.
     * @return true if the direction is horizontally leftwards,
     *         false otherwise.
     */
    public boolean isHorizontallyLeftwards() {
        return this == Direction.LEFTWARDS || this == Direction.LEFT_DOWNWARDS
                || this == Direction.LEFT_UPWARDS;
    }

    /**
     * Checks if the direction is horizontally rightwards,
     * defined as rightwards is its horizontal projection.
     * @return true if the direction is horizontally rightwards,
     *         false otherwise.
     */
    public boolean isHorizontallyRightwards() {
        return !this.isHorizontallyLeftwards() &&
                !this.isHorizontallyStatic();
    }

    /**
     * Checks if the direction is horizontally static,
     * defined as static is its horizontal projection.
     * @return true if the direction is horizontally static,
     *         false otherwise.
     */
    public boolean isHorizontallyStatic() {
        return this == Direction.DOWNWARDS || this == Direction.UPWARDS;
    }

    /**
     * Checks if the direction is vertically downwards,
     * defined as downwards is its vertical projection.
     * @return true if the direction is vertically downwards,
     *         false otherwise.
     */
    public boolean isVerticallyDownwards() {
        return this == Direction.DOWNWARDS || this == Direction.LEFT_DOWNWARDS
                || this == Direction.RIGHT_DOWNWARDS;
    }

    /**
     * Checks if the direction is vertically upwards,
     * defined as upwards is its vertical projection.
     * @return true if the direction is vertically upwards,
     *         false otherwise.
     */
    public boolean isVerticallyUpwards() {
        return !this.isVerticallyDownwards() &&
                !this.isVerticallyStatic();
    }

    /**
     * Checks if the direction is vertically static,
     * defined as static is its vertical projection.
     * @return true if the direction is vertically static,
     *         false otherwise.
     */
    public boolean isVerticallyStatic() {
        return this == Direction.LEFTWARDS || this == Direction.RIGHTWARDS;
    }
}
