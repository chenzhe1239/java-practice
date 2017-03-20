package com.janosgyerik.practice.oj.hackerrank.WeekOfCode30.SubstringQueries;

import java.util.*;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();

        String[] words = new String[n];
        for(int s_i=0; s_i < n; s_i++){
            words[s_i] = in.next();
        }

        String[] q1 = new String[q];
        String[] q2 = new String[q];
        for(int a0 = 0; a0 < q; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            q1[a0] = words[x];
            q2[a0] = words[y];
        }

        Input input = new Input(words, q1, q2);
        Solver solver = new Solver(input);
        for (int i = 0; i < q; i++) {
            System.out.println(solver.findLongestSubLength(q1[i], q2[i]));
        }
    }

    static class Solver {
        final Map<String, SuffixTree> map = new HashMap<>();

        public Solver(Input input) {
            Stream.of(input.words).forEach(w -> map.put(w, new SuffixTree(w)));
        }

        public int findLongestSubLength(String a, String b) {
            CombinationIterator it = new CombinationIterator(a);
            while (it.hasNext()) {
                String word = it.next();
                if (map.get(b).contains(word)) {
                    return word.length();
                }
            }
            return 0;
        }
    }

    static class CombinationIterator implements Iterator<String> {
        Queue<String> queue = new LinkedList<>();

        public CombinationIterator(String s) {
            queue.add(s);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
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
    }

    static class Input {
        private final String[] words;
        private final String[] q1;
        private final String[] q2;

        public Input(String[] words, String[] q1, String[] q2) {
            this.words = words;
            this.q1 = q1;
            this.q2 = q2;
        }
    }

    // n
    // en
    // ren
    // eren
    // ...

    // add: robieren
    // search for: "ere"

    //   root
    //  /  |  \       i      b
    // n   e   r       \      \
    //     | \   \  \     e    ...
    //     n  r   e  o    \
    //        |   |   \    r
    //        e   n   ...    \
    //        |              e
    //        n               \
    //                         n

    // longest substring of birkerem in probieren ?
    // all substrings of birkerem -> search in suffix tree

    // optimization #2
    // string -> char[], start, length -> avoid string generation

    // optimization #n -> generalized suffix trees with fast lookup of common ancestors
    // see https://en.wikipedia.org/wiki/Generalized_suffix_tree
    // https://en.wikipedia.org/wiki/Longest_common_substring_problem
    // https://en.wikipedia.org/wiki/Suffix_tree

    static class SuffixTree {

        final Node root = new Node('$');

        SuffixTree(String word) {
            create(word);
        }

        private void create(String word) {
            for (String suffix : suffixes(word)) {
                add(suffix);
            }
        }

        SuffixTree add(String suffix) {
            Node node = root;
            for (char c : suffix.toCharArray()) {
                node = node.getOrCreate(c);
            }
            return this;
        }

        private String[] suffixes(String word) {
            String[] suffixes = new String[word.length()];
            for (int i = 0; i < word.length(); i++) {
                suffixes[i] = word.substring(i);
            }
            return suffixes;
        }

        public Node get(String s) {
            return root;
        }

        public List<String> paths() {
            List<String> paths = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            paths(root, sb, paths);
            return paths;
        }

        private void paths(Node node, StringBuilder sb, List<String> paths) {
            if (node.isEmpty()) {
                paths.add(sb.toString());
                return;
            }
            for (Node child : node.children()) {
                paths(child, sb.append(child.c), paths);
                sb.setLength(sb.length() - 1);
            }
        }

        public boolean contains(String s) {
            Node node = root;
            for (char c : s.toCharArray()) {
                node = node.get(c);
                if (node == null) return false;
            }
            return true;
        }

        static class Node {
            private final char c;
            Map<Character, Node> children = new HashMap<>();

            Node(char c) {
                this.c = c;
            }

            public Node getOrCreate(char c) {
                return children.computeIfAbsent(c, k -> new Node(c));
            }

            boolean isEmpty() {
                return children.isEmpty();
            }

            public Collection<Node> children() {
                return children.values();
            }

            public Node get(char c) {
                return children.get(c);
            }
        }
    }
}
