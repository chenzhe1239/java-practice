package com.janosgyerik.practice.oj.answers.OpenTheLock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> stop = new HashSet<>(Arrays.asList(deadends));
        Set<String> seen = new HashSet<>();

        Queue<String> queue = new LinkedList<>();
        queue.add("0000");

        int depth = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                String value = queue.poll();
                if (value.equals(target)) {
                    return depth;
                }
                if (stop.contains(value) || seen.contains(value)) {
                    continue;
                }
                seen.add(value);

                for (int j = 0; j < value.length(); j++) {
                    char[] chars = value.toCharArray();
                    char c = chars[j];

                    if (c == '9') {
                        chars[j] = '0';
                    } else {
                        chars[j] = (char) (c + 1);
                    }
                    queue.add(new String(chars));

                    if (c == '0') {
                        chars[j] = '9';
                    } else {
                        chars[j] = (char) (c - 1);
                    }
                    queue.add(new String(chars));
                }
            }
            depth++;
        }
        return -1;
    }

    public int openLock(String target, String... deadends) {
        return openLock(deadends, target);
    }

    @Test
    public void test_example_1() {
        assertThat(openLock("0202", "0201", "0101", "0102", "1212", "2002")).isEqualTo(6);
    }

    @Test
    public void test_example_0009_8888() {
        assertThat(openLock("0009", "8888")).isEqualTo(1);
    }

    @Test
    public void test_example_3() {
        assertThat(openLock("8888", "8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888")).isEqualTo(-1);
    }

    @Test
    public void test_example_8888_0000() {
        assertThat(openLock("8888", "0000")).isEqualTo(-1);
    }
}
