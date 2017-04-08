package com.janosgyerik.practice.oj.gcj.y2017.qual;

import org.junit.*;

import java.util.*;

import static com.janosgyerik.practice.oj.gcj.y2017.qual.BathroomStalls.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BathroomStallsTest {

    Solver solver = new SkippingSolver(null);

    Answer solve(int n, int k) {
        return solver.solve(new Input(n, k));
    }

    @Test
    public void should_get_1_0_for_4_2() {
        assertThat(solve(4, 2)).isEqualTo(new Answer(1, 0));
    }

    @Test
    public void should_get_1_0_for_5_2() {
        assertThat(solve(5, 2)).isEqualTo(new Answer(1, 0));
    }

    @Test
    public void should_get_1_1_for_6_2() {
        assertThat(solve(6, 2)).isEqualTo(new Answer(1, 1));
    }

    @Test
    public void should_get_0_0_for_1000_1000() {
        assertThat(solve(1000, 1000)).isEqualTo(new Answer(0, 0));
    }

    @Test
    public void should_get_500_499_for_1000_1() {
        assertThat(solve(1000, 1)).isEqualTo(new Answer(500, 499));
    }

    @Test
    public void should_get_5000_4999_for_10000_1() {
        assertThat(solve(10000, 1)).isEqualTo(new Answer(5000, 4999));
    }

    @Test
    public void should_get_155_155_for_10000_50() {
        assertThat(solve(10000, 50)).isEqualTo(new Answer(155, 155));
    }

    @Test
    public void should_get_1_0_for_1000000_1000000_div_2() {
        assertThat(solve(1000000, 1000000 / 2)).isEqualTo(new Answer(1, 0));
    }

    //@Test
    public void test_() {
        int N = 10000;
        Answer prev = new Answer(0, 0);
        for (int i = 1; i < N; i++) {
            Answer answer = solve(N, i);
            if (!answer.equals(prev)) {
                System.out.print(i + ": ");
                System.out.println(answer);
            }
            prev = answer;
        }
    }

    String brute_10000 = "1: Answer{max=5000, min=4999}\n" +
            "2: Answer{max=2500, min=2499}\n" +
            "3: Answer{max=2499, min=2499}\n" +
            "4: Answer{max=1250, min=1249}\n" +
            "5: Answer{max=1249, min=1249}\n" +
            "8: Answer{max=625, min=624}\n" +
            "9: Answer{max=624, min=624}\n" +
            "16: Answer{max=312, min=312}\n" +
            "17: Answer{max=312, min=311}\n" +
            "32: Answer{max=156, min=155}\n" +
            "49: Answer{max=155, min=155}\n" +
            "64: Answer{max=78, min=77}\n" +
            "81: Answer{max=77, min=77}\n" +
            "128: Answer{max=39, min=38}\n" +
            "145: Answer{max=38, min=38}\n" +
            "256: Answer{max=19, min=19}\n" +
            "273: Answer{max=19, min=18}\n" +
            "512: Answer{max=9, min=9}\n" +
            "785: Answer{max=9, min=8}\n" +
            "1024: Answer{max=4, min=4}\n" +
            "1809: Answer{max=4, min=3}\n" +
            "2048: Answer{max=2, min=1}\n" +
            "3857: Answer{max=1, min=1}\n" +
            "4096: Answer{max=1, min=0}\n" +
            "5905: Answer{max=0, min=0}\n";

    @Test
    public void test_example() {
        String input = "5\n" +
                "4 2\n" +
                "5 2\n" +
                "6 2\n" +
                "1000 1000\n" +
                "1000 1";

        String expected = "Case #1: 1 0\n" +
                "Case #2: 1 0\n" +
                "Case #3: 1 1\n" +
                "Case #4: 0 0\n" +
                "Case #5: 500 499\n";

        MultiSolver msolver = new MultiSolver(solver);
        Inputs inputs = Inputs.parse(new Scanner(input));
        assertThat(msolver.solve(inputs)).isEqualTo(expected);
    }
}
