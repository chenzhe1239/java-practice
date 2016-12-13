package com.janosgyerik.practice.oj.adventofcode.y2016.day5;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class GameOfChessTest {
    String input = "ojvtpuvg";

    static class PasswordIterator implements Iterator<byte[]> {
        private final MessageDigest md = MessageDigest.getInstance("MD5");
        private final String input;
        private long count = 0;

        PasswordIterator(String input) throws NoSuchAlgorithmException {
            this.input = input;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public byte[] next() {
            while (true) {
                byte[] digest = md.digest((input + count).getBytes());
                count++;
                if (startsWithFiveZeros(digest)) {
                    return digest;
                }
            }
        }

        private boolean startsWithFiveZeros(byte[] digest) {
            return digest[0] == 0 && digest[1] == 0 && (digest[2] & 240) == 0;
        }

    }

    private Character sixth(byte[] digest) {
        return toChar(nth(digest, 5));
    }

    @Test
    public void test_first_3_digits_for_abc_is_18f() throws NoSuchAlgorithmException {
        Iterator<byte[]> iterator = new PasswordIterator("abc");
        String first3 = "" + sixth(iterator.next()) + sixth(iterator.next()) + sixth(iterator.next());
        assertThat(first3).isEqualTo("18f");
    }

    @Test
    public void test_cracked_ojvtpuvg_is_4543c154() throws NoSuchAlgorithmException {
        Iterator<byte[]> iterator = new PasswordIterator(input);
        String pw = "";
        int i = 0;
        while (iterator.hasNext()) {
            pw += sixth(iterator.next());
            if (i++ == 7) break;
        }
        assertThat(pw).isEqualTo("4543c154");
    }

    String crackPart2(String input) throws NoSuchAlgorithmException {
        Iterator<byte[]> iterator = new PasswordIterator(input);
        char[] bytes = new char[8];
        int i = 0;
        while (iterator.hasNext()) {
            byte[] digest = iterator.next();
            int pos = nth(digest, 5);
            if (pos < 8) {
                if (bytes[pos] == 0) {
                    bytes[pos] = toChar(nth(digest, 6));
                    if (i++ == 7) break;
                }
            }
        }
        return new String(bytes);
    }

    @Test
    public void test_cracked_part2_example() throws NoSuchAlgorithmException {
        assertThat(crackPart2("abc")).isEqualTo("05ace8e3");
    }

    @Test
    public void test_cracked_part2_final() throws NoSuchAlgorithmException {
        assertThat(crackPart2(input)).isEqualTo("1050cbbd");
    }

    private int nth(byte[] digest, int n) {
        byte b = digest[n / 2];
        if (n % 2 == 0) {
            return (b & 0xf0) >> 4;
        }
        return b & 0xf;
    }

    private char toChar(int b) {
        if (b < 10) {
            return (char)('0' + b);
        }
        return (char)('a' + b - 10);
    }
}
