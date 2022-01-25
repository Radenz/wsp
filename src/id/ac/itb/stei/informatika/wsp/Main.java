package id.ac.itb.stei.informatika.wsp;

import id.ac.itb.stei.informatika.wsp.type.*;
import id.ac.itb.stei.informatika.wsp.io.*;

import java.util.Scanner;

/**
 * {@code Main} is the entry point of word search puzzle solver program.
 * @author Raden Rifqi Rahman
 */
public class Main {

    /**
     * Entry point of the program.
     * @param args CLI arguments.
     */
    public static void main(String[] args) {

        System.out.println();

        String path = "";
        boolean optimized = false;

        switch (args.length) {
            case 0:
                System.out.print("Input file path: ");
                Scanner scanner = new Scanner(System.in);
                boolean valid = false;
                path = scanner.nextLine();
                while (!valid) {
                    System.out.print("Use optimization ? (Y/n) ");
                    String choice = scanner.nextLine();
                    if (choice.equals("Y") || choice.equals("y")) {
                        optimized = true;
                        valid = true;
                    } else if (choice.equals("N") || choice.equals("n")) {
                        valid = true;
                    }
                }
                System.out.println();
                break;
            case 2:
                String option = args[1];
                if (option.equals("--optimize")) {
                    optimized = true;
                } else {
                    Main.printInvalidArgsErrorMsg();
                }
            case 1:
                path = args[0];
                break;
            default:
                Main.printInvalidArgsErrorMsg();
        }

        FileReader reader = new FileReader();
        String fileContent = "";
        if (reader.readFile(path)) {
            fileContent = reader.result();
        } else {
            System.out.println("File not found.");
            System.exit(1);
        }

        InputParser parser = new InputParser();

        if (parser.parse(fileContent)) {
            Matrix<Character> puzzle = parser.getPuzzle();
            String[] words = parser.getWords();

            long startTime = System.nanoTime();

            Solver solver = new Solver(puzzle);
            if (optimized) {
                solver.optimize();
            }
            ColoredMatrix<Character> solution = new ColoredMatrix<>(puzzle);
            int[] comparisons = new int[words.length];
            Color[] colors = new Color[words.length];

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                SolveResult result = solver.search(word);
                Color color = Color.random();
                if (!result.foundAt().equals(Solver.UNDEFINED_COORD)) {
                    solution.colorize(
                            result.foundAt(),
                            result.direction(),
                            word.length(),
                            color
                            );
                    colors[i] = color;
                } else {
                    colors[i] = Color.PLAIN;
                }

                comparisons[i] = result.comparisons();
            }

            long endTime = System.nanoTime();
            long execTimeNs = endTime - startTime;
            double execTimeMs = (double) execTimeNs / 1000000;
            double execTimeSeconds = execTimeMs / 1000;

            System.out.println("SOLUTION");
            System.out.println(solution);

            System.out.println("SUMMARY");
            int totalComparisons = 0;
            for (int i = 0; i < words.length; i++) {
                System.out.print(colors[i].wrap(words[i]) + " - " + comparisons[i] + " comparisons");
                if (colors[i] == Color.PLAIN) {
                    System.out.print(" (not found)");
                }
                System.out.println();
                totalComparisons += comparisons[i];
            }
            System.out.println("Total comparisons : " + totalComparisons + " comparisons");
            System.out.println("Execution time    : " + execTimeMs + " ms");
            System.out.println("                    " + execTimeSeconds + " s");

        } else {
            System.out.println("Invalid input format.");
            System.exit(1);
        }
    }

    private static void printInvalidArgsErrorMsg() {
        System.out.println("\nUsage : [input-file-path [--optimize]]\n");
        System.out.println("  --optimize\t\t\tOptimize solving algorithm using" +
                " heuristic technique.");
        System.exit(0);
    }
}
