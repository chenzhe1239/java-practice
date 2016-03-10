package com.janosgyerik.practice.misc.geneva;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FirstRepatedWord {
    static String firstRepeatedWord(String s) {
        Iterator<String> wordIterator = new Iterator<String>() {
            int pos = nextAlphabeticPos(s, 0);

            int length = s.length();

            final StringBuilder builder = new StringBuilder();
            @Override
            public boolean hasNext() {
                return pos < length;
            }

            @Override
            public String next() {
                builder.setLength(0);
                do {
                    char c = s.charAt(pos++);
                    if (!Character.isAlphabetic(c)) {
                        pos = nextAlphabeticPos(s, pos);
                        break;
                    }
                    builder.append(c);
                } while (pos < length);

                return builder.toString();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("removing words is not supported");
            }

            private int nextAlphabeticPos(String s, int start) {
                int target = start;
                while (target < length && !Character.isAlphabetic(s.charAt(target))) {
                    ++target;
                }
                return target;
            }
        };

        Set<String> seen = new HashSet<>();

        while (wordIterator.hasNext()) {
            String word = wordIterator.next();
            if (!seen.add(word)) {
                return word;
            }
        }

        throw new IllegalStateException("It is guaranteed that there will be at least one repeated");
    }

    @Test
    public void repeated_had() {
        assertEquals("had", firstRepeatedWord("He had had quite enough of this nonsense"));
    }

    @Test
    public void repeated_had_with_trailing_whitespace() {
        assertEquals("had", firstRepeatedWord("He had had    "));
    }

    @Test
    public void repeated_had_with_excess_whitespace() {
        assertEquals("had", firstRepeatedWord("He    had had    "));
    }
}
