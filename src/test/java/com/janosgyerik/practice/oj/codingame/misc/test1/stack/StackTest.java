package com.janosgyerik.practice.oj.codingame.misc.test1.stack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StackTest {
    @Test
    public void testPushingAndPopping() {
        Stack stack = new Stack(3);
        List<Integer> ints = Arrays.asList(3, 6, 1, 9, 3);
        for (Integer num : ints) {
            stack.push(num);
        }
        Collections.reverse(ints);
        for (Integer num : ints) {
            assertEquals(num, stack.pop());
        }
    }

    @Test
    public void testPushDups() {
        Stack stack = new Stack(3);
        List<Integer> ints = Arrays.asList(1, 3, 3, 3, 6);
        for (Integer num : ints) {
            stack.push(num);
        }
        Collections.reverse(ints);
        for (Integer num : ints) {
            assertEquals(num, stack.pop());
        }
    }

    @Test
    public void testPushAndPopMany() {
        Stack stack = new Stack(10000);
        for (int i = 0; i < 10000; i++) { // fill the stack
            stack.push("a large, large, large, large, string " + i);
        }
        for (int i = 0; i < 10000; i++) { // empty the stack
            stack.pop();
//            System.out.println(stack.pop());
        }
    }
}
