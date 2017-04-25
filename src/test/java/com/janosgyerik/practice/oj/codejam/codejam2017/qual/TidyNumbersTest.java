package com.janosgyerik.practice.oj.codejam.codejam2017.qual;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TidyNumbersTest {
    TidyNumbers tn = new TidyNumbers();

    private String solve(String s) {
        return tn.findLastTidy(s);
    }

    @Test
    public void first_untidy_pos_in_123332_should_be_4() {
        assertThat(tn.findFirstUntidyPos("123332".toCharArray())).isEqualTo(4);
    }

    @Test
    public void first_same_pos_in_123332_4_should_be_2() {
        assertThat(tn.findFirstSamePos("123332".toCharArray(), 4)).isEqualTo(2);
    }

    @Test
    public void should_get_7_for_7() {
        assertThat(solve("7")).isEqualTo("7");
    }

    @Test
    public void should_get_99999999999999999_for_111111111111111110() {
        assertThat(solve("111111111111111110")).isEqualTo("99999999999999999");
    }

    @Test
    public void should_get_122999_for_123332() {
        assertThat(solve("123332")).isEqualTo("122999");
    }

    @Test
    public void should_get_999_for_1000() {
        assertThat(solve("1000")).isEqualTo("999");
    }

    @Test
    public void should_get_129_for_132() {
        assertThat(solve("132")).isEqualTo("129");
    }

    @Test
    public void should_get_11_for_11() {
        assertThat(solve("11")).isEqualTo("11");
    }

    @Test
    public void should_get_1_for_1() {
        assertThat(solve("1")).isEqualTo("1");
    }

    @Test
    public void should_get_122999_for_123123() {
        assertThat(solve("123123")).isEqualTo("122999");
    }
}
