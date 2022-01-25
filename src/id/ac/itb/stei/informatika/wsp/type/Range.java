package id.ac.itb.stei.informatika.wsp.type;

/**
 * {@code Range} represents an increasing integer values
 * from a specific start value to a specific end value,
 * inclusively.
 */
public class Range {
    /**
     * Inclusive lower bound of the range.
     */
    public int start;
    /**
     * Inclusive upper bound of the range.
     */
    public int end;

    /**
     * Creates a new {@code Range} based on the start value
     * and end value.
     * @param start start value.
     * @param end end value.
     */
    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Converts the {@code Range} object to an array enumerating
     * all values within the range.
     * @return an array enumerating all values within the range.
     */
    public int[] toArray() {
        int[] list = new int[this.end - this.start + 1];
        for (int i = start; i <= end; i++) {
            list[i - start] = i;
        }
        return list;
    }
}
