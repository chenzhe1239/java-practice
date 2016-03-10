package com.janosgyerik.practice.codility.easy;

import java.util.Stack;

public class Brackets {
    public int submit(String s) {
        if (s.isEmpty()) {
            return 1;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            switch (c) {
                case '{':
                case '(':
                case '[':
                    stack.push(c);
                    break;
                case ']':
                case ')':
                case '}':
                    if (stack.isEmpty()) {
                        return 0;
                    }
                    char opening = stack.pop();
                    if (!isMatchingClose(opening, c)) {
                        return 0;
                    }
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }

    private boolean isMatchingClose(char opening, char closing) {
        switch (opening) {
            case '(':
                return closing == ')';
            case '{':
                return closing == '}';
            case '[':
                return closing == ']';
        }
        return false;
    }
}
