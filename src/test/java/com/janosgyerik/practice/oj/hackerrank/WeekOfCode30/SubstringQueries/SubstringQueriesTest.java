package com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.SubstringQueries;

import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.SubstringQueries.Solution.Input;
import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.SubstringQueries.Solution.Solver;
import com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.SubstringQueries.Solution.SuffixTree;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SubstringQueriesTest {
    @Test
    public void empty_suffix_tree_should_have_no_paths() {
        assertThat(new SuffixTree("").paths()).containsExactly("");
    }

    @Test
    public void suffix_tree_with_one_suffix_should_have_one_path() {
        String suffix = "heap";
        assertThat(new SuffixTree("").add(suffix).paths()).containsExactly(suffix);
    }

    @Test
    public void suffix_tree_of_heap_should_have_p_ap_eap_heap() {
        String word = "heap";
        assertThat(new SuffixTree(word).paths()).containsExactlyInAnyOrder(word, "p", "eap", "ap");
    }

    @Test
    public void suffix_tree_contains() {
        String word = "heap";
        SuffixTree tree = new SuffixTree(word);
        Iterator<String> it = new Iterator<String>() {
            Queue<String> queue = new LinkedList<>(Collections.singleton(word));

            @Override
            public boolean hasNext() {
                return queue.isEmpty();
            }

            @Override
            public String next() {
                String ret = queue.poll();
                if (!ret.isEmpty()) {
                    queue.add(ret.substring(1));
                    queue.add(ret.substring(0, ret.length() - 1));
                }
                return ret;
            }
        };
        while (it.hasNext()) {
            String sub = it.next();
            assertThat(tree.contains(sub)).isTrue();
            assertThat(tree.contains(sub + "$")).isFalse();
        }
    }

    @Test
    public void test_solver() {
        String[] words = {"probieren", "birkerem", "sadasment"};
        Input input = new Input(words, null, null);
        Solver solver = new Solver(input);
        assertThat(solver.findLongestSubLength("probieren", "birkerem")).isEqualTo(3);
        assertThat(solver.findLongestSubLength("birkerem", "sadasment")).isEqualTo(1);
        assertThat(solver.findLongestSubLength("probieren", "sadasment")).isEqualTo(2);
    }
}
