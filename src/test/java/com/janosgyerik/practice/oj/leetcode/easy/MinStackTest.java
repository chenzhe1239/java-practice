package com.janosgyerik.practice.oj.leetcode.easy;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

class MinStack {
    private int min = 0;
    private int max = 0;
    private LinkedList<Integer> stack = new LinkedList<>();

    public void push(int x) {
        if (stack.isEmpty()) {
            min = max = x;
        } else if (x < min) {
            min = x;
        } else if (max < x) {
            max = x;
        }
        stack.push(x);
    }

    public void pop() {
        int removed = stack.pop();
        if (removed == min) {
            min = max;
            for (int x : stack) {
                if (x < min) {
                    min = x;
                }
            }
        }
    }

    public int top() {
        return stack.getFirst();
    }

    public int getMin() {
        return min;
    }
}

public class MinStackTest {
    @Test
    public void testPushGet() {
        MinStack stack = new MinStack();
        int x = 13;
        stack.push(1);
        stack.push(2);
        stack.push(x);
        assertEquals(x, stack.top());
    }

    @Test
    public void testMinFirst() {
        MinStack stack = new MinStack();
        int x = 13;
        stack.push(1);
        stack.push(2);
        stack.push(x);
        assertEquals(1, stack.getMin());
    }

    @Test
    public void testMinLast() {
        MinStack stack = new MinStack();
        int x = 13;
        stack.push(2);
        stack.push(x);
        stack.push(1);
        assertEquals(1, stack.getMin());
    }

    @Test
    public void testExample() {
        MinStack stack = new MinStack();
        stack.push(2);
        stack.push(0);
        stack.push(3);
        stack.push(0);
        assertEquals(0, stack.getMin());
        stack.pop();
        assertEquals(0, stack.getMin());
        stack.pop();
        assertEquals(0, stack.getMin());
        stack.pop();
        assertEquals(2, stack.getMin());
    }
}
