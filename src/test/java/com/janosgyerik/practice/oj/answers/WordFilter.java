package com.janosgyerik.practice.oj.answers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordFilter {

    final String[] words;
    final Index index = new Index();

    public WordFilter(String[] words) {
        this.words = words;
        IntStream.range(0, words.length).forEach(i -> index.add(words[i], i));
    }

    public int f(String prefix, String suffix) {
        SortedSet<Integer> w1 = index.pWeights(prefix);
        for (Integer w : index.sWeights(suffix)) {
            if (w1.contains(w)) {
                return w;
            }
        }
        return -1;
    }

    static class Index {
        Map<String, SortedSet<Integer>> pIndex = new HashMap<>();
        Map<String, SortedSet<Integer>> sIndex = new HashMap<>();

        SortedSet<Integer> empty = new TreeSet<>();

        void add(String word, int weight) {
            for (int i = 0; i < word.length() + 1; i++) {
                String prefix = word.substring(0, i);
                String suffix = word.substring(i);
                pIndex.computeIfAbsent(prefix, s -> new TreeSet<>(Comparator.reverseOrder())).add(weight);
                sIndex.computeIfAbsent(suffix, s -> new TreeSet<>(Comparator.reverseOrder())).add(weight);
            }
        }

        SortedSet<Integer> pWeights(String prefix) {
            return pIndex.getOrDefault(prefix, empty);
        }

        SortedSet<Integer> sWeights(String suffix) {
            return sIndex.getOrDefault(suffix, empty);
        }
    }
}

