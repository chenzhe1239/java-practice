package com.janosgyerik.practice.oj.answers;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Answers {
    @Test
    public void test_computeIfAbsent() {
        Map<String, List<Character>> index = new HashMap<>();
        index.computeIfAbsent("ab", k -> new ArrayList<>()).add('c');
        index.computeIfAbsent("ab", k -> new ArrayList<>()).add('d');
        System.out.println(index);
    }

    @Test
    public void test_path_concat() {
        System.out.println(new File("hello", "there").toString());
    }

    public int[] mapper(int[] A, int[] B) {
        Map<Integer, Integer> map =
            IntStream.range(0, B.length).boxed().collect(Collectors.toMap(i -> B[i], i -> i, (i, j) -> j));
        return Arrays.stream(A).map(map::get).toArray();
    }

    @Test
    public void test_mapper() {
        System.out.println(Arrays.toString(mapper(new int[]{12, 28, 46, 32, 50}, new int[]{50, 12, 32, 46, 28})));
        System.out.println(Arrays.toString(mapper(new int[]{12, 12}, new int[]{12, 12})));
    }

    @Test
    public void test_networkDelayTime() {

    }

    static class Graph {
        List<Link>[] map;

        Graph(int N) {
            map = IntStream.range(0, N + 1).mapToObj(i -> new ArrayList<>()).toArray((IntFunction<ArrayList<Link>[]>) ArrayList[]::new);
        }

        void add(int from, int to, int weight) {
            map[from].add(new Link(from, to, weight));
        }

        int delay(int K) {
            for (Link link : map[K]) {

            }
            return 1;
        }
    }

    static class Link {
        int from;
        int to;
        int weight;

        Link(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public int maxChunksToSorted(int... arr) {
        int count = 0;
        int start = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == sum(start, i)) {
                count++;
                start = i + 1;
                sum = 0;
            }
        }
        return count;
    }

    private int sum(int start, int end) {
        return (end + start) * (end - start + 1) / 2;
    }

    @Test
    public void test_sum_0_0_is_0() {
        assertThat(sum(0, 0)).isEqualTo(0);
    }

    @Test
    public void test_sum_2_2_is_2() {
        assertThat(sum(2, 2)).isEqualTo(2);
    }

    @Test
    public void test_sum_2_3_is_5() {
        assertThat(sum(2, 3)).isEqualTo(5);
    }

    @Test
    public void test_sum_2_4_is_9() {
        assertThat(sum(2, 4)).isEqualTo(9);
    }

    @Test
    public void test_4_3_2_1_0() {
        assertThat(maxChunksToSorted(4, 3, 2, 1, 0)).isEqualTo(1);
    }

    @Test
    public void test_1_0_2_3_4() {
        assertThat(maxChunksToSorted(1, 0, 2, 3, 4)).isEqualTo(4);
    }

    @Test
    public void test_1_0_3_2_4() {
        assertThat(maxChunksToSorted(1, 0, 3, 2, 4)).isEqualTo(3);
    }

    public int climbStairs(int n) {
        int count = 1;
        int maxPossibleTwoSteps = n / 2;
        for (int i = 1; i <= maxPossibleTwoSteps; ++i) {
            int numberOfStepsIncludingNTwoSteps = getNumberOfStepsIncludingNTwoSteps(n, i);
            count += nCr(numberOfStepsIncludingNTwoSteps, i);
        }
        return count;
    }

    private int getNumberOfStepsIncludingNTwoSteps(int n, int i) {
        return i + (n - i * 2);
    }

    private int nCr(int n, int r) {
        return factorial(n, r).divide(factorial(n - r)).intValue();
    }

    private BigInteger factorial(int n) {
        BigInteger product = BigInteger.ONE;
        for (int i = 2; i <= n; ++i) {
            product = product.multiply(BigInteger.valueOf(i));
        }
        return product;
    }

    private BigInteger factorial(int n, int r) {
        BigInteger product = BigInteger.ONE;
        for (int i = r + 1; i <= n; ++i) {
            product = product.multiply(BigInteger.valueOf(i));
        }
        return product;
    }

    @Test
    public void test_climb() {
        // too slow!
        //assertThat(climbStairs(100000)).isEqualTo(1);
        assertThat(climbStairs(3)).isEqualTo(3);
        assertThat(climbStairs(4)).isEqualTo(5);
        assertThat(climbStairs(5)).isEqualTo(8);
        assertThat(climbStairs(6)).isEqualTo(13);
        StringBuilder sb = new StringBuilder();

        List<Integer> list = Arrays.asList(1, 2, 3);
        list.stream().mapToInt(x -> 1).toArray();
    }

    static class Elevation {
        int x;
        int top;

        Elevation(int x, int top) {
            this.x = x;
            this.top = top;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, top);
        }
    }

    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> maxHeights = new ArrayList<>();

        int gmax = 0;

        List<Elevation> elevations = new ArrayList<>();
        elevations.add(new Elevation(0, 0));

        for (int[] square : positions) {
            int start = square[0];
            int end = start + square[1];
            int lmax = lmax(elevations, start, end) + square[1];
            update(elevations, start, end, lmax);
            // 1,2 2,3 6,1

            gmax = Math.max(gmax, lmax);
            maxHeights.add(gmax);
        }

        return maxHeights;
    }

    int lmax(List<Elevation> elevations, int start, int end) {
        int startx = index(elevations, start);
        if (startx == elevations.size()) {
            return elevations.get(startx - 1).top;
        }

        int endx = index(elevations, end);
        if (endx == 1) {
            return 0;
        }

        int lmax = lmax(elevations, start);
        for (int i = startx; i < endx; i++) {
            lmax = Math.max(lmax, elevations.get(i).top);
        }
        return lmax;
    }

    int lmax(List<Elevation> elevations, int x) {
        int startx = index(elevations, x);
        if (startx < elevations.size() && elevations.get(startx).x == x) return elevations.get(startx).top;
        return elevations.get(startx - 1).top;
    }

    void update(List<Elevation> elevations, int start, int end, int lmax) {
        int startx = index(elevations, start);
        if (startx == elevations.size()) {
            elevations.add(new Elevation(start, lmax));
            elevations.add(new Elevation(end, 0));
            return;
        }

        int endx = index(elevations, end);
        if (endx == 1) {
            elevations.add(1, new Elevation(end, 0));
            elevations.add(1, new Elevation(start, lmax));
            return;
        }

        int emax = lmax(elevations, end);

        // remove all entries with start <= e.x < end
        for (int i = startx; i < endx; i++) {
            elevations.remove(startx);
        }

        // insert an entry (start, lmax)
        elevations.add(startx, new Elevation(start, lmax));

        // if the next entry has e.x == end we're done
        if (startx + 1 < elevations.size() && elevations.get(startx + 1).x == end) return;

        // otherwise insert (end, lmax(end))
        elevations.add(startx + 1, new Elevation(end, emax));
    }

    int index(List<Elevation> elevations, int start) {
        int x = Collections.binarySearch(elevations, new Elevation(start, 0), (a, b) -> Integer.compare(a.x, b.x));
        if (x < 0) {
            return -1 - x;
        }
        return x;
    }

    @Test
    public void test_falling_squares() {
        assertThat(fallingSquares(new int[][]{{1, 2}, {2, 3}, {6, 1}})).isEqualTo(Arrays.asList(2, 5, 5));
        assertThat(fallingSquares(new int[][]{{1, 2}, {3, 3}, {6, 1}})).isEqualTo(Arrays.asList(2, 3, 3));
        assertThat(fallingSquares(new int[][]{{1, 2}, {2, 5}, {6, 1}})).isEqualTo(Arrays.asList(2, 7, 8));
        assertThat(fallingSquares(new int[][]{{1, 2}, {2, 5}, {7, 1}})).isEqualTo(Arrays.asList(2, 7, 7));
        assertThat(fallingSquares(new int[][]{{1, 5}, {2, 4}, {3, 3}})).isEqualTo(Arrays.asList(5, 9, 12));
    }

    @Test
    public void test_parser() {
        System.out.println("hello".matches(".*l.*"));
        assertThat(wrappedInValidClosedTag("<A></A>x")).isFalse();
        assertThat(wrappedInValidClosedTag("<A></A>")).isTrue();
        assertThat(wrappedInValidClosedTag("<HELLOTHER>hello</HELLOTHER>")).isTrue();
        assertThat(wrappedInValidClosedTag("<HELLOTHERE></HELLOTHERE>")).isFalse();
        assertThat(wrappedInValidClosedTag("<AB></A>")).isFalse();
    }

    boolean wrappedInValidClosedTag(String code) {
        return code.matches("^<([A-Z]{1,9})>.*</\\1>$");
    }

    boolean containsInvalidTagName(String code) {
        if (code.matches(".*<>.*")) return true;
        for (String tag : extractTags(code)) {
            if (!tag.matches("</?[A-Z]{1,9}>")) return true;
        }
        return false;
//        return code.matches(".*(<[^/A-Z])|(<[A-Z]+?[^A-Z]+[A-Z]*?>)|(</[A-Z]*?[^A-Z]+[A-Z]*?>)|(<>)|(</?[A-Z]{10,}>).*");
    }

    @Test
    public void test_containsInvalidTag() {
        assertThat(containsInvalidTagName("<>")).isTrue();
        assertThat(containsInvalidTagName("<hello>")).isTrue();
        assertThat(containsInvalidTagName("<HELLO>")).isFalse();
        assertThat(containsInvalidTagName("<HELLOx>")).isTrue();
        assertThat(containsInvalidTagName("<HELxLO>")).isTrue();
        assertThat(containsInvalidTagName("<xHELLO>")).isTrue();
        assertThat(containsInvalidTagName("<HELLOTHERE>")).isTrue();
        assertThat(containsInvalidTagName("<DIV></DIV>")).isFalse();
    }

    boolean containsUnmatchedOpener(String code) {
        return code.replaceAll("<[^>]+>", "").matches(".*<.*");
    }

    @Test
    public void test_containsUnmatchedOpener() {
        assertThat(containsUnmatchedOpener("<")).isTrue();
        assertThat(containsUnmatchedOpener("<he")).isTrue();
        assertThat(containsUnmatchedOpener("x<")).isTrue();
        assertThat(containsUnmatchedOpener("x<y")).isTrue();
        assertThat(containsUnmatchedOpener("x<y>y")).isFalse();
    }

    List<String> extractTags(String code) {
        List<String> tags = new ArrayList<>();
        Matcher m = Pattern.compile("<[^>]+>").matcher(code);
        while (m.find()) {
            tags.add(m.group());
        }
        return tags;
    }

    @Test
    public void test_extractTags() {
        String code = "<A>  <B> </A>   </B>";
        assertThat(extractTags(code)).isEqualTo(Arrays.asList("<A>", "<B>", "</A>", "</B>"));
    }

    boolean isBalanced(List<String> tags) {
        Deque<String> stack = new ArrayDeque<>();
        for (String tag : tags) {
            if (tag.startsWith("</")) {
                if (stack.isEmpty()) return false;
                if (!stack.pop().substring(1).equals(tag.substring(2))) return false;
            } else {
                stack.push(tag);
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test_isBalanced() {
        assertThat(isBalanced(extractTags("<A>  <B> </A>   </B>"))).isFalse();
        assertThat(isBalanced(extractTags("<A>  <B> </B>   </A>"))).isTrue();
    }

    public boolean isValid(String code) {
        if (!wrappedInValidClosedTag(code)) return false;

        code = stripCdata(code);

        if (containsInvalidTagName(code)) return false;

        if (containsUnmatchedOpener(code)) return false;

        List<String> tags = extractTags(code);
        return isBalanced(tags);
    }

    String stripCdata(String code) {
        return code.replaceAll("<!\\[CDATA\\[.*?\\]\\]>", "");
    }

    @Test
    public void test_solution() {
        assertThat(isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>")).isTrue();
    }

    public List<String> findAllConcatenatedWordsInADict(String... words) {
        Set<String> unique = Stream.of(words).filter(w -> !w.isEmpty()).collect(Collectors.toSet());

        Set<String> result = new HashSet<>();

        for (String word : unique) {
            check(unique, result, word, 0);
        }

        return new ArrayList<>(result);
    }

    private void check(Set<String> unique, Set<String> result, String word, int start) {
        if (start == word.length() || (start > 0 && unique.contains(word.substring(start)))) {
            result.add(word);
            return;
        }

        for (int end = start + 1; end <= word.length(); end++) {
            if ((start > 0 || end < word.length()) && unique.contains(word.substring(start, end))) {
                check(unique, result, word, end);
            }
        }
    }

    @Test
    public void test_find_all_concat() {
        assertThat(findAllConcatenatedWordsInADict("cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"))
            .containsExactlyInAnyOrder("catsdogcats", "dogcatsdog", "ratcatdogcat");

        assertThat(findAllConcatenatedWordsInADict("")).isEmpty();

        assertThat(findAllConcatenatedWordsInADict("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaaa", "aaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
            .containsExactlyInAnyOrder("catsdogcats", "dogcatsdog", "ratcatdogcat");
    }

    @Test
    public void test_substring() {
        addOperators("123", 6);
        System.out.println(addOperators("105", 5));
    }

    public List<String> addOperators(String num, int target) {
        if (num.isEmpty()) return Collections.emptyList();

        List<String> result = new ArrayList<>();

        for (int i = 1; i <= num.length(); i++) {
            int b;
            try {
                b = Integer.parseInt(num.substring(0, i));
            } catch (NumberFormatException e) {
                continue;
            }
            if (!String.valueOf(b).equals(num.substring(0, i))) continue;
            String rest = num.substring(i);
            addOperators(b, rest, target, "" + b, result);
        }

        return result;
    }

    public void addOperators(int left, String num, int target, String expression, List<String> result) {
        if (num.isEmpty()) {
            if (evaluate(expression) == target) result.add(expression);
            return;
        }

        for (int i = 1; i <= num.length(); i++) {
            int b = Integer.parseInt(num.substring(0, i));
            if (!String.valueOf(b).equals(num.substring(0, i))) continue;
            String rest = num.substring(i);
            addOperators(left + b, rest, target, expression + "+" + b, result);
            addOperators(left - b, rest, target, expression + "-" + b, result);
            addOperators(left * b, rest, target, expression + "*" + b, result);
        }
    }

    private int evaluate(String expression) {
        class Evaluator {
            private final String expression;
            private int index = 0;

            public Evaluator(String expression) {
                this.expression = expression;
            }

            public int nextInt() {
                int start = index;
                for (; index < expression.length(); index++) {
                    if (!Character.isDigit(expression.charAt(index))) {
                        return Integer.parseInt(expression.substring(start, index));
                    }
                }
                return Integer.parseInt(expression.substring(start));
            }

            public char nextOperator() {
                return expression.charAt(index++);
            }

            public boolean finished() {
                return index == expression.length();
            }
        }

        Evaluator evaluator = new Evaluator(expression);
        int value = evaluator.nextInt();

        List<Object> list = new ArrayList<>();
        while (!evaluator.finished()) {
            char operator = evaluator.nextOperator();
            if (operator == '*') {
                value *= evaluator.nextInt();
            } else if (operator == '+' || operator == '-') {
                list.add(value);
                list.add(operator);
                value = evaluator.nextInt();
            }
        }
        list.add(value);

        value = (Integer) list.get(0);
        for (int i = 1; i < list.size(); i += 2) {
            char op = (Character) list.get(i);
            int v2 = (Integer) list.get(i + 1);
            if (op == '+') {
                value += v2;
            } else {
                value -= v2;
            }
        }

        return value;
    }

    @Test
    public void test_evaluate() {
        assertThat(evaluate("1+2+3")).isEqualTo(6);
        assertThat(evaluate("1+2*3")).isEqualTo(7);
        assertThat(evaluate("1+2*3+1")).isEqualTo(8);
    }

    public static void rearrange2(int[] a) {
        int n = a.length;
        int i = 0, j = 0;
        while (i < n) {
            if (i % 2 == 0) {
                if (a[i] > 0) {
                    i++;
                } else {
                    j = i + 1;
                    while (j < n) {
                        if (a[j] > 0) {
                            break;
                        }
                        j++;
                    }
                    rotateright(a, i, j);
                    i++;
                }
            } else {
                if (a[i] < 0) {
                    i++;
                } else {
                    j = i + 1;
                    while (j < n) {
                        if (a[j] < 0) {
                            break;
                        }
                        j++;
                    }
                    rotateright(a, i, j);
                    i++;
                }
            }
        }
    }


    public static void rearrange(int[] a) {
        int n = a.length;
        int i = 0, j = 0;
        while (i < n) {
            if (i % 2 == 0)    //even index should have positive element
            {
                if (a[i] > 0)     //already positive
                    i++;
                else               //for negative
                {
                    j = i + 1;
                    while (j < n)      //finding next positive
                    {
                        if (a[j] > 0)
                            break;
                        j++;
                    }
                    rotateright(a, i, j);    //making even index positive
                    i++;
                }
            } else            //odd index should have negative element
            {
                if (a[i] < 0)   //already negative
                    i++;
                else           //for positive
                {
                    j = i + 1;
                    while (j < n)          //finding next negative
                    {
                        if (a[j] < 0)
                            break;
                        j++;
                    }
                    rotateright(a, i, j);     //making odd index negative
                    i++;
                }
            }
        }
    }

    public static void rotateright(int arr[], int left, int right) {
        int temp = arr[right];
        System.arraycopy(arr, left, arr, left + 1, right - left);
        arr[left] = temp;
    }

    int[] rearrangeWrapper(int... arr) {
        rearrange(arr);
        return arr;
    }

    @Test
    public void test_rearrange() {
        assertThat(rearrangeWrapper(1, 2, 3, -1, -2, -3)).isEqualTo(new int[]{1, -1, 2, -2, 3, -3});
    }

    @Test
    public void test_rearrange_stress() {
        int len = 200000;
        int[] input = genInput(len);
        int[] target = genTarget(len);
        assertThat(rearrangeBetter(input)).isEqualTo(target);
//        assertThat(rearrangeWrapper(input)).isEqualTo(target);
    }

    int[] rearrangeBetter(int[] arr) {
        int nextPositiveIndex = 0;
        int nextNegativeIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                if (arr[i] < 0) {
                    for (nextPositiveIndex = Math.max(nextPositiveIndex, i) + 1; nextPositiveIndex < arr.length; nextPositiveIndex++) {
                        if (arr[nextPositiveIndex] > 0) break;
                    }
                    rotateright(arr, i, nextPositiveIndex);
                }
            } else {
                if (arr[i] > 0) {
                    for (nextNegativeIndex = Math.max(nextNegativeIndex, i) + 1; nextNegativeIndex < arr.length; nextNegativeIndex++) {
                        if (arr[nextNegativeIndex] < 0) break;
                    }
                    rotateright(arr, i, nextNegativeIndex);
                }
            }
        }
        return arr;
    }

    int[] rearrangeBetter2(int[] arr) {
        IntFunction<Boolean> isPositive = v -> v > 0;
        IntFunction<Boolean> isNegative = v -> v < 0;
        for (int i = 0; i < arr.length; i++) {
            IntFunction<Boolean> cmp = i % 2 == 0 ? isPositive : isNegative;
            if (!cmp.apply(arr[i])) {
                int j = i + 1;
                while (j < arr.length) {
                    if (cmp.apply(arr[j]))
                        break;
                    j++;
                }
                rotateright(arr, i, j);
            }
        }
        return arr;
    }


    private int[] genTarget(int len) {
        int[] arr = new int[len];
        for (int i = 0, x = 1; i < len; i += 2) {
            arr[i] = x;
            arr[i + 1] = -x;
            x++;
        }
        return arr;
    }

    private int[] genInput(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len / 2; i++) {
            arr[i] = i + 1;
            arr[len / 2 + i] = -i - 1;
        }
        return arr;
    }

    @Test
    public void test_print_char_array() {
        String s = "hello";
        System.out.println(s.toCharArray());
    }
}
