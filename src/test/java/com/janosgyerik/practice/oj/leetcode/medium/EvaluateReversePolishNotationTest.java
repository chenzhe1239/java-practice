package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class EvaluateReversePolishNotationTest {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            int op1, op2;
            switch (token) {
                case "+":
                    op2 = stack.pop();
                    op1 = stack.pop();
                    stack.push(op1 + op2);
                    break;
                case "-":
                    op2 = stack.pop();
                    op1 = stack.pop();
                    stack.push(op1 - op2);
                    break;
                case "*":
                    op2 = stack.pop();
                    op1 = stack.pop();
                    stack.push(op1 * op2);
                    break;
                case "/":
                    op2 = stack.pop();
                    op1 = stack.pop();
                    stack.push(op1 / op2);
                    break;
                default:
                    int number = Integer.parseInt(token);
                    stack.push(number);
            }
        }
        return stack.pop();
    }

    @Test
    public void test_2_1_p_3_s() {
        assertEquals(9, evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }

    @Test
    public void test_4_13_5_d_p() {
        assertEquals(6, evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }
}
