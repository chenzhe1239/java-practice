package com.janosgyerik.practice.oj.codejam.codejam2017.round1a.AlphabetCake;

import com.janosgyerik.practice.oj.codejam.codejam2017.common.*;

import java.io.IOException;
import java.util.Scanner;

public class AlphabetCake implements Problem {
    @Override
    public Inputs inputs(Scanner scanner) {
        Inputs inputs = new Inputs();

        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            scanner.nextLine();

            char[][] matrix = new char[rows][];
            for (int row = 0; row < rows; row++) {
                matrix[row] = scanner.nextLine().toCharArray();
            }
            inputs.add(new AlphabetCakeInput(rows, cols, matrix));
        }
        return inputs;
    }

    static class AlphabetCakeInput implements Input {
        private final int rows;
        private final int cols;
        private final char[][] matrix;

        AlphabetCakeInput(int rows, int cols, char[][] matrix) {
            this.rows = rows;
            this.cols = cols;
            this.matrix = matrix;
        }
    }

    @Override
    public Solver solver(Inputs inputs) {
        return new AlphabetCakeSolver();
    }

    static class AlphabetCakeSolver implements Solver {
        @Override
        public Answer solve(Input input0) {
            AlphabetCakeInput input = (AlphabetCakeInput) input0;

            fill(input.matrix);

            return new AlphabetCakeAnswer(input.matrix);
        }

        private void fill(char[][] matrix) {
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    char c = matrix[row][col];
                    if (c != '?') {
                        fillHorizontally(matrix, c, row, col);
                    }
                }
            }

            for (int i = 1; i < matrix.length; i++) {
                if (matrix[i][0] == '?' && matrix[i - 1][0] != '?') {
                    matrix[i] = matrix[i - 1].clone();
                }
            }

            for (int i = matrix.length - 2; i >= 0; i--) {
                if (matrix[i][0] == '?' && matrix[i + 1][0] != '?') {
                    matrix[i] = matrix[i + 1].clone();
                }
            }
        }

        private void fillHorizontally(char[][] matrix, char target, int row, int col) {
            for (int i = col - 1; i >= 0 && matrix[row][i] == '?'; i--) {
                matrix[row][i] = target;
            }
            for (int i = col + 1; i < matrix[row].length && matrix[row][i] == '?'; i++) {
                matrix[row][i] = target;
            }
        }
    }

    static class AlphabetCakeAnswer implements Answer {
        private final char[][] matrix;

        public AlphabetCakeAnswer(char[][] matrix) {
            this.matrix = matrix;
        }

        @Override
        public String value() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (char[] row : matrix) {
                sb.append(row).append("\n");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        String input = "A-large-practice.in";
//        String input = "A-small-practice.in";

        Runner runner = Runner.create(new AlphabetCake());
        runner.run(input);
    }

}
