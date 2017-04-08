package com.janosgyerik.practice.oj.hackerrank.PoisonousPlants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestClass {

    private int cookies(int n, int m) {
        if (m % n == 0) return 0;
        return n - (m % n);
    }

    @Test
    public void test_cookies_1_1() {
        assertEquals(0, cookies(1, 1));
    }

    @Test
    public void test_cookies_2_2() {
        assertEquals(0, cookies(2, 2));
    }

    @Test
    public void test_cookies_10_1() {
        assertEquals(9, cookies(10, 1));
    }

}
