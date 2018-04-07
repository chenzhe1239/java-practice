package com.janosgyerik.practice.oj.codejam.codejam2018.SavingTheUniverseAgain;

import java.util.Scanner;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void test_computeDamage() {
        assertThat(solution.computeDamage("CS")).isEqualTo(2);
        assertThat(solution.computeDamage("SS")).isEqualTo(2);
        assertThat(solution.computeDamage("SCCSSC")).isEqualTo(9);
        assertThat(solution.computeDamage("CC")).isEqualTo(0);
        assertThat(solution.computeDamage("CSCSS")).isEqualTo(10);
    }

    @Test
    public void test_findMinSwaps() {
        assertThat(solution.findMinSwaps("CS", 1)).isEqualTo(1);
        assertThat(solution.findMinSwaps("CS", 2)).isEqualTo(0);
        assertThat(solution.findMinSwaps("SS", 1)).isEqualTo(-1);
        assertThat(solution.findMinSwaps("SCCSSC", 6)).isEqualTo(2);
        assertThat(solution.findMinSwaps("CC", 2)).isEqualTo(0);
        assertThat(solution.findMinSwaps("CSCSS", 3)).isEqualTo(5);
        assertThat(solution.findMinSwaps("SSSCC", 3)).isEqualTo(0);
        assertThat(solution.findMinSwaps("CCSSS", 3)).isEqualTo(6);
        assertThat(solution.findMinSwaps("CCSSSS", 4)).isEqualTo(8);
        assertThat(solution.findMinSwaps("CCSSSS", 3)).isEqualTo(-1);
        assertThat(solution.findMinSwaps("CCSSSS", 16)).isEqualTo(0);
        assertThat(solution.findMinSwaps("CCSSSS", 15)).isEqualTo(1);
        assertThat(solution.findMinSwaps("CSCSCS", 3)).isEqualTo(6);
        assertThat(solution.findMinSwaps("SCSCSC", 3)).isEqualTo(3);
        assertThat(solution.findMinSwaps("SCCSCS", 3)).isEqualTo(5);
        assertThat(solution.findMinSwaps("SSCCCS", 3)).isEqualTo(3);
        assertThat(solution.findMinSwaps("CSSCSS", 3)).isEqualTo(-1);
        assertThat(solution.findMinSwaps("CSSCSS", 4)).isEqualTo(6);
        assertThat(solution.findMinSwaps("CSSSCSSSCSSS", 4)).isEqualTo(-1);
        assertThat(solution.findMinSwaps("CSSSCSSSCSSS", 9)).isEqualTo(18);
        assertThat(solution.findMinSwaps("CSSSCSSSCSSS", 15)).isEqualTo(12);
        assertThat(solution.findMinSwaps("CSSSCSSSCSSS", 99)).isEqualTo(0);
        assertThat(solution.findMinSwaps("SSS", 99)).isEqualTo(0);
        assertThat(solution.findMinSwaps("SSS", 2)).isEqualTo(-1);
        assertThat(solution.findMinSwaps("SSSC", 2)).isEqualTo(-1);
        assertThat(solution.findMinSwaps("SSSC", 3)).isEqualTo(0);
        assertThat(solution.findMinSwaps("SSCS", 3)).isEqualTo(1);
        assertThat(solution.findMinSwaps("SCSS", 3)).isEqualTo(2);
        assertThat(solution.findMinSwaps("CSSS", 3)).isEqualTo(3);
        assertThat(solution.findMinSwaps("CCCCCS", 11)).isEqualTo(2);
        assertThat(solution.findMinSwaps("CCCSCC", 7)).isEqualTo(1);
    }

    @Test
    public void test_off_by_one() {
        assertThat(solution.findMinSwaps("CSSSCSSSCSSS", 15)).isEqualTo(12);
    }

    @Test
    public void test_solve() {
        String input = "6\n" +
            "1 CS\n" +
            "2 CS\n" +
            "1 SS\n" +
            "6 SCCSSC\n" +
            "2 CC\n" +
            "3 CSCSS";
        solution.solve(new Scanner(input));
    }
}
