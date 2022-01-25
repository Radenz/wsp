package id.ac.itb.stei.informatika.wsp.io;

import id.ac.itb.stei.informatika.wsp.type.Matrix;

/**
 * {@code CharacterMatrixParser} is a parser that parses
 * a string input with specific format to a matrix of
 * characters.
 * Each row in the string input must be separated with a
 * newline and each column/character must be separated
 * with a whitespace.
 */
public class CharacterMatrixParser {

    private Matrix<Character> result = null;

    /**
     * Parses a string to a matrix of characters.
     * @param input input string.
     * @return true if parsing succeeds, false if the
     *         input format is incorrect.
     */
    public boolean parse(String input) {
        String[] rawRowArray = input.split("\n");
        int rows = rawRowArray.length;
        int cols = rawRowArray[0].split("\s").length;
        this.result = new Matrix<>(rows, cols);

        boolean failed = false;

        filling:
        for (int i = 0; i < rows; i++) {
            String[] rawColArray = rawRowArray[i].split("\s");
            for (int j = 0; j < cols; j++) {
                char[] charArray = rawColArray[j].toCharArray();
                if (charArray.length != 1) {
                    failed = true;
                    break filling;
                } else {
                    this.result.set(i, j, charArray[0]);
                }
            }
        }

        return !failed;
    }

    /**
     * Returns the parsing result.
     * @return parsing result.
     */
    public Matrix<Character> result() {
        return this.result;
    }
}
