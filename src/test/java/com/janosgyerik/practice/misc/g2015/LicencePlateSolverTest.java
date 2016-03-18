package com.janosgyerik.practice.misc.g2015;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class LicencePlateSolverTest {

    private static LicencePlateSolver solver = LicencePlateSolver
            .fromWords(new HashSet<>(Arrays.asList("hello", "hi", "szia")));

    //    @BeforeClass
    //    public static void setUpBeforeClass() throws IOException {
    //        solver = LicencePlateSolver.fromWordsFile("src/test/resources/words-barrons.txt");
    //    }

    @Test
    public void test_abc_123() {
        assertEquals(null, solver.findShortestWord("abc-123"));
    }

    @Test
    public void test_eho_123() {
        assertEquals("hello", solver.findShortestWord("eho 123"));
    }

    @Test
    public void test_echo_123() {
        assertEquals(null, solver.findShortestWord("echo 123"));
    }

    @Test
    public void test_h_123() {
        assertEquals("hi", solver.findShortestWord("h 123"));
    }

    @Test
    public void test_hx_123() {
        assertEquals(null, solver.findShortestWord("haz 123"));
    }
}
