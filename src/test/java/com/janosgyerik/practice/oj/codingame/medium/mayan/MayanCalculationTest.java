package com.janosgyerik.practice.oj.codingame.medium.mayan;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MayanCalculationTest {
    @Test
    public void testExtractNumeral() {
        List<String> alphabet = Arrays.asList(
                ".oo.o...oo..ooo.oooo....o...oo..ooo.oooo____o...oo..ooo.oooo____o...oo..ooo.oooo",
                "o..o................____________________________________________________________",
                ".oo.........................................____________________________________",
                "................................................................________________");

        MayanNumeral mayanZero = new MayanNumeral(Arrays.asList(
           ".oo.",
           "o..o",
           ".oo.",
           "...."
        ));
        assertEquals(mayanZero, MayanCalculator.extractNumeral(alphabet, 4, 0));

        MayanNumeral mayanOne = new MayanNumeral(Arrays.asList(
           "o...",
           "....",
           "....",
           "...."
        ));
        assertEquals(mayanOne, MayanCalculator.extractNumeral(alphabet, 4, 1));

        MayanNumeral mayan14 = new MayanNumeral(Arrays.asList(
           "oooo",
           "____",
           "____",
           "...."
        ));
        assertEquals(mayan14, MayanCalculator.extractNumeral(alphabet, 4, 14));
    }

    @Test
    public void testIntValue() {
        List<String> alphabet = Arrays.asList(
                ".oo.o...oo..ooo.oooo....o...oo..ooo.oooo____o...oo..ooo.oooo____o...oo..ooo.oooo",
                "o..o................____________________________________________________________",
                ".oo.........................................____________________________________",
                "................................................................________________");
        MayanCalculator calculator = MayanCalculator.fromAlphabet(alphabet, 4);

        MayanNumeral mayanZero = new MayanNumeral(Arrays.asList(
           ".oo.",
           "o..o",
           ".oo.",
           "...."
        ));
        assertEquals(0, calculator.intValue(mayanZero));
    }

    @Test
    public void testNumeralValue() {
        List<String> alphabet = Arrays.asList(
                ".oo.o...oo..ooo.oooo....o...oo..ooo.oooo____o...oo..ooo.oooo____o...oo..ooo.oooo",
                "o..o................____________________________________________________________",
                ".oo.........................................____________________________________",
                "................................................................________________");
        MayanCalculator calculator = MayanCalculator.fromAlphabet(alphabet, 4);

        MayanNumeral mayan21 = new MayanNumeral(Arrays.asList(
           "o...",
           "....",
           "....",
           "....",
           "o...",
           "....",
           "....",
           "...."
        ));
        assertEquals(mayan21, calculator.numeralValue(21));
    }
}
