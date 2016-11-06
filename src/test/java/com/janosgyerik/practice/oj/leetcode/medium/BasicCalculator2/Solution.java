package com.janosgyerik.practice.oj.leetcode.medium.BasicCalculator2;

import java.util.ArrayDeque;
import java.util.function.BiFunction;

public class Solution {
    static class Operator implements BiFunction<Integer, Integer, Integer> {
        private final BiFunction<Integer, Integer, Integer> op;
        private final boolean lower;

        Operator(BiFunction<Integer, Integer, Integer> op, boolean lower) {
            this.op = op;
            this.lower = lower;
        }

        @Override
        public Integer apply(Integer n1, Integer n2) {
            return op.apply(n1, n2);
        }

        boolean isLower() {
            return lower;
        }
    }

    static class Tokenizer {
        int pos = 0;
        final char[] chars;

        Tokenizer(String s) {
            chars = s.toCharArray();
            skip();
        }

        void skip() {
            for (; pos < chars.length; pos++) {
                if (chars[pos] != ' ') {
                    break;
                }
            }
        }

        boolean hasNext() {
            return pos != chars.length;
        }

        Operator nextOp() {
            char c = chars[pos++];
            Operator op;
            switch (c) {
                case '+':
                    op = new Operator((n1, n2) -> n1 + n2, true);
                    break;
                case '-':
                    op = new Operator((n1, n2) -> n1 - n2, true);
                    break;
                case '*':
                    op = new Operator((n1, n2) -> n1 * n2, false);
                    break;
                case '/':
                    op = new Operator((n1, n2) -> n1 / n2, false);
                    break;
                default:
                    throw new IllegalStateException("unexpected operator: " + c);
            }
            skip();
            return op;
        }

        int nextNum() {
            int num = 0;
            for (; pos < chars.length; pos++) {
                char c = chars[pos];
                if ('0' <= c && c <= '9') {
                    int digit = c - '0';
                    num *= 10;
                    num += digit;
                } else {
                    break;
                }
            }
            skip();
            return num;
        }
    }

    public int calculate(String s) {
        Tokenizer tokenizer = new Tokenizer(s);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(tokenizer.nextNum());

        Operator prevOp = new Operator((n1, n2) -> { throw new IllegalStateException(); }, false);
        while (tokenizer.hasNext()) {
            Operator op = tokenizer.nextOp();
            int num = tokenizer.nextNum();
            if (op.isLower()) {
                if (stack.size() > 1) {
                    int n2 = stack.pop();
                    int n1 = stack.pop();
                    stack.push(prevOp.apply(n1, n2));
                }
                prevOp = op;
                stack.push(num);
            } else {
                stack.push(op.apply(stack.pop(), num));
            }
        }
        if (stack.size() > 1) {
            int n2 = stack.pop();
            int n1 = stack.pop();
            stack.push(prevOp.apply(n1, n2));
        }
        return stack.pop();
    }
}
