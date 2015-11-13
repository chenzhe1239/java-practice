package com.janosgyerik.practice.oj.leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RestoreIpAddressesTest {
    public List<String> restoreIpAddresses(String s) {
        List<String> addresses = new LinkedList<>();
        for (List<Integer> widths : getPossibleWidths(s.length())) {
            List<String> segments = getSegments(s, widths);
            if (isValidSegments(segments)) {
                addresses.add(joinWithDots(segments));
            }
        }
        return addresses;
    }

    List<String> getSegments(String string, List<Integer> widths) {
        List<String> segments = new LinkedList<>();
        for (int start = 0, i = 0; i < widths.size(); ++i) {
            int width = widths.get(i);
            int end = start + width;
            segments.add(string.substring(start, end));
            start = end;
        }
        return segments;
    }

    String joinWithDots(List<String> segments) {
        StringBuilder builder = new StringBuilder();
        builder.append(segments.get(0));
        for (int i = 1; i < segments.size(); ++i) {
            builder.append('.').append(segments.get(i));
        }
        return builder.toString();
    }

    boolean isValidSegments(List<String> segments) {
        for (String segment : segments) {
            if (segment.length() > 1 && segment.startsWith("0")) {
                return false;
            }
            if (Integer.parseInt(segment) > 255) {
                return false;
            }
        }
        return true;
    }

    int sum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

    List<List<Integer>> getPossibleWidths(int len) {
        List<List<Integer>> results = new LinkedList<>();

        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList());

        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();
            if (list.size() == 4) {
                if (sum(list) == len) {
                    results.add(list);
                }
                continue;
            }
            for (int i = 1; i <= 3; ++i) {
                List<Integer> copy = new LinkedList<>(list);
                copy.add(i);
                queue.add(copy);
            }
        }
        return results;
    }
}
