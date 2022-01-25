package id.ac.itb.stei.informatika.wsp;

import id.ac.itb.stei.informatika.wsp.type.Direction;
import id.ac.itb.stei.informatika.wsp.type.Point;

/**
 * {@code SolveResult} is the result of searching a word in
 * {@code Solver}.
 */
public record SolveResult(Point foundAt, Direction direction, int comparisons) {
}
