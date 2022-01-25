package id.ac.itb.stei.informatika.wsp.io;

import id.ac.itb.stei.informatika.wsp.type.Matrix;

/**
 * {@code InputParser} parses a raw string input to a character matrix
 * as the puzzle and an array of strings as words to search from the
 * puzzle.
 */
public class InputParser {

    private Matrix<Character> puzzle = null;
    private String[] words;

    /**
     * Creates a new InputParser.
     */
    public InputParser() {}

    /**
     * Parse a raw input string.
     * @param input input string.
     * @return true if parsing succeeds, false otherwise.
     */
    public boolean parse(String input) {
        String[] chunks = input.split("\n\n");
        if (chunks.length != 2) {
            return false;
        } else {
            String rawPuzzle = chunks[0];
            CharacterMatrixParser cmp = new CharacterMatrixParser();
            if (cmp.parse(rawPuzzle)) {
                this.puzzle = cmp.result();
            } else {
                return false;
            }

            String rawWords = chunks[1];
            return this.parseWords(rawWords);
        }
    }

    /**
     * Parses a raw string to an array of string (words).
     * @param rawWords input string.
     * @return true if parsing succeeds, false otherwise.
     */
    private boolean parseWords(String rawWords) {
        this.words = rawWords.split("\n");
        for (String word: this.words) {
            if (word.equals("")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieve the puzzle from parsing result.
     * @return a matrix of characters representing a puzzle.
     */
    public Matrix<Character> getPuzzle() {
        return this.puzzle;
    }

    /**
     * Retrieve the words from parsing result.
     * @return an array of string representing words to search.
     */
    public String[] getWords() {
        return this.words;
    }
}
