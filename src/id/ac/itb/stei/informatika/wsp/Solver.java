package id.ac.itb.stei.informatika.wsp;

import id.ac.itb.stei.informatika.wsp.type.*;

/**
 * Class {@code Solver} is a word finder for a word search puzzle.
 * Each solver instance can only hold one puzzle and can not be
 * changed for its lifetime, but can be reused to search multiple
 * words in a single puzzle.
 */
public class Solver {

    /**
     * Undefined puzzle matrix row and column indices.
     */
    public static final Point UNDEFINED_COORD = new Point(-1, -1);

    private static final Direction[] DIRECTIONS = {
            Direction.RIGHTWARDS,
            Direction.DOWNWARDS,
            Direction.RIGHT_DOWNWARDS,
            Direction.RIGHT_UPWARDS,
            Direction.UPWARDS,
            Direction.LEFTWARDS,
            Direction.LEFT_UPWARDS,
            Direction.LEFT_DOWNWARDS,
    };
    private final Matrix<Character> puzzle;
    private int comparisons = 0;
    private boolean optimization = false;

    /**
     * Creates a new {@code Solver} for a specified puzzle.
     * @param puzzle word search puzzle to solve.
     */
    public Solver(Matrix<Character> puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Optimizes bruteforce searching algorithm using
     * heuristic technique.
     */
    public void optimize() {
        this.optimization = true;
    }

    /**
     * Searches a word in the solver's puzzle.
     * @param string word to search in the puzzle.
     * @return search result containing the row and column indices
     *         of where the first character the word is found at,
     *         the search direction, and total character comparisons.
     *         Returns {@code Solver.UNDEFINED_COORD} row and column
     *         indices if the word is not found.
     */
    public SolveResult search(String string) {
        this.comparisons = 0;
        Point res = Solver.UNDEFINED_COORD;
        Direction direction = Direction.LEFTWARDS;
        for (Direction dir: Solver.DIRECTIONS) {
            direction = dir;
            res = this.searchAtDirection(string, dir);
            if (!res.equals(Solver.UNDEFINED_COORD)) {
                break;
            }
        }
        return new SolveResult(res, direction, this.comparisons);
    }

    /**
     * Calculates all possible column indices of the starting
     * letter based on word length and search direction.
     * @param length the length of word to search.
     * @param direction search direction.
     * @return a {@code Range} containing all possible column indices
     *         of the starting letter.
     */
    private Range getHorizontalRange(int length, Direction direction) {
        if (direction.isHorizontallyStatic() || !this.optimization) {
            return new Range(0, this.puzzle.cols() - 1);
        } else {
            int increment = length - 1;
            int start = direction.isHorizontallyLeftwards()
                    ? increment
                    : 0;
            int end = this.puzzle.cols() - 1;
            end -= direction.isHorizontallyRightwards()
                    ? increment
                    : 0;
            return new Range(start, end);
        }
    }

    /**
     * Calculates all possible row indices of the starting
     * letter based on word length and search direction.
     * @param length the length of word to search.
     * @param direction search direction.
     * @return a {@code Range} containing all possible row indices
     *         of the starting letter.
     */
    private Range getVerticalRange(int length, Direction direction) {
        if (direction.isVerticallyStatic() || !this.optimization) {
            return new Range(0, this.puzzle.rows() - 1);
        } else {
            int increment = length - 1;
            int start = direction.isVerticallyUpwards()
                    ? increment
                    : 0;
            int end = this.puzzle.rows() - 1;
            end -= direction.isVerticallyDownwards()
                    ? increment
                    : 0;
            return new Range(start, end);
        }
    }

    /**
     * Searches a word with specified direction in the solver's puzzle.
     * @param string word to search in the puzzle.
     * @param direction search direction.
     * @return a {@code Point} object indicating the row and column indices
     *         of where the first letter of the word is found at. Returns
     *         {@code Solver.UNDEFINED_COORD} if the word is not found.
     */
    private Point searchAtDirection(String string, Direction direction) {
        Range hRange = this.getHorizontalRange(string.length(), direction);
        Range vRange = this.getVerticalRange(string.length(), direction);
        for (int i : vRange.toArray()) {
            for (int j : hRange.toArray()) {
                boolean match = this.matchAt(string, new Point(i, j), direction);
                if (match) {
                    return new Point(i, j);
                }
            }
        }
        return Solver.UNDEFINED_COORD;
    }

    /**
     * Matches the word to search with a word in the puzzle at specified
     * starting letter indices and search direction. This method compares
     * every character in the word to search and character in the puzzle
     * with specific indices incremental based on search direction.
     * For example, in the following puzzle
     * <pre>
     * A B C D
     * E F G H
     * I J K L
     * M N O P</pre>
     * if a 3-letter word were to be matched at (1, 0) starting letter indices
     * with {@code Direction.RIGHT_DOWNWARDS} direction, then the first letter
     * will be compared with E, the second will be compared with J, and the third
     * will be compared with O.
     * @param string word to search in the puzzle.
     * @param coord starting letter indices to match.
     * @param direction search direction.
     * @return true if the word to search match with the characters in the puzzle,
     *         false otherwise.
     */
    private boolean matchAt(String string, Point coord, Direction direction) {

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
        int length = string.length();

        int x = coord.x();
        int y = coord.y();
        int i = 0;
        boolean match = true;

        while (match && i < length) {
            // Index out of bounds guard
            if (!this.puzzle.hasIndices(x, y)) {
                match = false;
                break;
            }

            char charToSearch = string.charAt(i);
            char charInPuzzle = this.puzzle.get(x, y);

            this.comparisons++;
            if (charToSearch == charInPuzzle) {
                i++;
                x += vIncrement;
                y += hIncrement;
            } else {
                match = false;
            }
        }

        return match;
    }
}
