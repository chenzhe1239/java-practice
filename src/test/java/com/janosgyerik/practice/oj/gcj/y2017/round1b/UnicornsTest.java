package com.janosgyerik.practice.oj.gcj.y2017.round1b;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UnicornsTest {
    @Test
    public void test_() {
        StringBuilder sb = new StringBuilder("hello");
        sb.setLength(sb.length() - 1);
        System.out.println(sb);

        List<String> list = new ArrayList<>(Arrays.asList("hello", "there"));
        list.retainAll(Collections.singleton("there"));
        System.out.println(list);
    }
}
