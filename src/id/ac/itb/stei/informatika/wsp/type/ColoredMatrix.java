package id.ac.itb.stei.informatika.wsp.type;

/**
 * {@code ColoredMatrix} holds a generic type matrix and
 * a {@code Color} matrix, enabling its elements to be
 * printed with various colors defined in the color matrix.
 * @param <T> type of the {@code ColoredMatrix} elements.
 * @see id.ac.itb.stei.informatika.wsp.type.Matrix
 */
public class ColoredMatrix<T> {

    private final Matrix<T> matrix;
    private final Matrix<Color> colorScheme;

    /**
     * Creates a new {@code ColoredMatrix} from an existing
     * {@code Matrix} object.
     * @param matrix {@code Matrix} object.
     */
    public ColoredMatrix(Matrix<T> matrix) {
        this.matrix = matrix;
        this.colorScheme = new Matrix<>(matrix.rows(), matrix.cols());
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.cols(); j++) {
                this.colorScheme.set(i, j, Color.PLAIN);
            }
        }
    }

    /**
     * Colorize the matrix at specified starting point, length,
     * direction, and color.
     * @param coord     elements' starting indices to colorize.
     * @param direction colorizing direction to colorize multiple
     *                  elements.
     * @param length    the amount of elements to colorize.
     * @param color     the color to colorize the elements.
     */
    public void colorize(Point coord, Direction direction, int length, Color color) {
        int hIncrement = direction.isHorizontallyStatic()
                ? 0
                : direction.isHorizontallyRightwards()
                ? 1
                : -1;
        int vIncrement = direction.isVerticallyStatic()
                ? 0
                : direction.isVerticallyDownwards()
                ? 1
                : -1;

        int x = coord.x();
        int y = coord.y();

        for (int i = 0; i < length; i++) {
            this.colorScheme.set(x, y, color);
            x += vIncrement;
            y += hIncrement;
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.matrix.rows(); i++) {
            for (int j = 0; j < this.matrix.cols(); j++) {
                if (j != 0) {
                    res += " ";
                }
                String filler = this.matrix.get(i, j).toString();
                res += this.colorScheme.get(i, j).wrap(filler);
            }
            res += "\n";
        }
        return res;
    }
}

