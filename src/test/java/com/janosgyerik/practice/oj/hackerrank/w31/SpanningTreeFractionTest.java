package com.janosgyerik.practice.oj.hackerrank.w31;

import com.janosgyerik.practice.oj.hackerrank.w31.Solution.Rational;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.*;

public class SpanningTreeFractionTest {
    @Test
    public void test_gcd() {
        assertThat(Rational.gcd(15, 10)).isEqualTo(5);
        assertThat(Rational.gcd(2, 3)).isEqualTo(1);
        assertThat(Rational.gcd(15, 12)).isEqualTo(3);
    }

    @Test
    public void test_reduced() {
        assertThat(new Rational(15, 12).reduced().toString()).isEqualTo("5/4");
    }

    @Test
    public void test_example_1() {
        Solution.Graph g = new Solution.Graph();
        g.connect(0, 1, new Rational(1, 1));
        g.connect(1, 2, new Rational(2, 4));
        g.connect(2, 0, new Rational(1, 2));
        Rational max = g.findMax();
        assertThat(max.num).isEqualTo(2);
        assertThat(max.denom).isEqualTo(3);
    }
}
