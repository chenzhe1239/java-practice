package com.janosgyerik.practice.oj.leetcode.hard.BurstBalloons;

import org.junit.Test;

import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class BurstBalloonsTest {
    private final Solution solution = new Solution();

    private int solve(int... nums) {
        return solution.maxCoins(nums);
    }

    private int solve(String s) {
        int[] arr = Stream.of(s.replaceAll("[^0-9,]", "").split(",")).mapToInt(Integer::parseInt).toArray();
        return solve(arr);
    }

    @Test
    public void test_one_balloon_3() {
        int balloon = 3;
        assertEquals(balloon, solve(balloon));
    }

    @Test
    public void test_balloons_2_3() {
        int b2 = 2;
        int b3 = 3;
        int expected = b2 * b3 + b3;
        assertEquals(expected, solve(b2, b3));
    }

    @Test
    public void test_balloons_3_1_5_8() {
        int expected = 167;
        assertEquals(expected, solve(3, 1, 5, 8));
    }

    @Test
    public void should_get_167_for_3_1_5_8() {
        assertEquals(167, solve(3, 1, 5, 8));
    }

    @Test
    public void should_get_0_for_empty() {
        assertEquals(0, solve());
    }

    @Test
    public void should_get_7_for_7() {
        assertEquals(7, solve(7));
    }

    @Test
    public void should_get_21_for_7_2() {
        assertEquals(21, solve(7, 2));
    }

    @Test
    public void should_get_27_for_1_9_1() {
        assertEquals(27, solve(1, 9, 1));
    }

    @Test
    public void should_get_44416_for_9_76_64() {
        assertEquals(44416, solve(9, 76, 64));
    }

    @Test
    public void should_get_1086136_for_9_76_64_21_97_60() {
        assertEquals(1086136, solve(9, 76, 64, 21, 97, 60));
    }

    int compute(int[] nums_, int... eliminate) {
        List<Integer> nums = IntStream.of(nums_).boxed().collect(Collectors.toList());
        int sum = 0;
        for (int num : eliminate) {
            int index = nums.indexOf(num);
            int left = 1;
            int right = 1;
            if (index > 0) {
                left = nums.get(index - 1);
            }
            if (index < nums.size() - 1) {
                right = nums.get(index + 1);
            }
            sum += left * nums.get(index) * right;
            nums.remove(index);
        }
        return sum;
    }

    int compute2(int[] nums_, int... eliminate) {
        List<Integer> nums = IntStream.of(nums_).boxed().collect(Collectors.toList());
        boolean[] done = new boolean[eliminate.length];
        int sum = 0;
        for (int index : eliminate) {
            int left = 0;
            for (int i = index - 1; i >= 0; i--) {
                if (!done[i]) {
                    left = nums.get(i);
                    break;
                }
            }
            if (left == 0) left = 1;

            int right = 0;
            for (int i = index + 1; i < done.length; i++) {
                if (!done[i]) {
                    right = nums.get(i);
                    break;
                }
            }
            if (right == 0) right = 1;

            done[index] = true;
            sum += left * nums.get(index) * right;
        }
        return sum;
    }

    //    @Test
    public void test_compute() {
        assertThat(compute(new int[]{3, 1, 5, 8}, 1, 5, 3, 8)).isEqualTo(167);
//        assertThat(compute(new int[]{9, 76, 64, 21, 97, 60}, 21, 64, 76, 9, 60, 97)).isEqualTo(1086136);
//        assertThat(compute(new int[]{9, 76, 64, 21, 97, 60}, 21, 64, 76, 9, 60, 97)).isEqualTo(1086136);
        List<Integer> nums = Arrays.asList(9, 76, 64, 21, 97, 60);
        List<List<Integer>> all = new ArrayList<>();

        Queue<List<Integer>> perms = new LinkedList<>();
        Queue<List<Integer>> choices = new LinkedList<>();
        perms.add(Collections.emptyList());
        choices.add(nums);

        while (!perms.isEmpty()) {
            List<Integer> perm = perms.poll();
            List<Integer> ch = choices.poll();
            if (ch.isEmpty()) {
                all.add(perm);
                continue;
            }
            for (int choice : ch) {
                List<Integer> permCopy = new ArrayList<>(perm);
                List<Integer> chCopy = new ArrayList<>(ch);
                permCopy.add(choice);
                perms.add(permCopy);
                chCopy.remove((Integer) choice);
                choices.add(chCopy);
            }
        }
        int[] x = nums.stream().mapToInt(i -> i).toArray();
        for (List<Integer> list : all) {
            int[] arr = list.stream().mapToInt(i -> i).toArray();
            int val = compute(x, arr);
            if (val > 1080000) {
                System.out.println(Arrays.toString(arr));
                System.out.println(val);
            }
        }
    }

    int[] findBestSequence(int... nums_) {
        List<Integer> nums = IntStream.of(nums_).boxed().collect(Collectors.toList());
        List<List<Integer>> all = new ArrayList<>();

        Queue<List<Integer>> perms = new LinkedList<>();
        Queue<List<Integer>> choices = new LinkedList<>();
        perms.add(Collections.emptyList());
        choices.add(nums);

        while (!perms.isEmpty()) {
            List<Integer> perm = perms.poll();
            List<Integer> ch = choices.poll();
            if (ch.isEmpty()) {
                all.add(perm);
                continue;
            }
            for (int choice : ch) {
                List<Integer> permCopy = new ArrayList<>(perm);
                List<Integer> chCopy = new ArrayList<>(ch);
                permCopy.add(choice);
                perms.add(permCopy);
                chCopy.remove((Integer) choice);
                choices.add(chCopy);
            }
        }
        int[] x = nums.stream().mapToInt(i -> i).toArray();
        int[] best = null;
        int max = 0;
        for (List<Integer> list : all) {
            int[] arr = list.stream().mapToInt(i -> i).toArray();
            int val = compute(x, arr);
            if (val > max) {
                max = val;
                best = arr;
            }
        }
        return best;
    }

//    @Test
    public void test_() {
//        System.out.println(compute2(new int[]{5, 4, 3, 4, 3, 4, 5}, 5, 1, 4, 2, 3, 0, 6));
//        System.out.println(compute(new int[]{9, 76, 64, 21, 97, 60}, 21, 64, 97, 76, 9, 60));
//        System.out.println(Arrays.toString(findBestSequence(2, 4, 6, 5, 3)));
        System.out.println(Arrays.toString(findBestSequence(3, 5, 6, 4, 2)));
//        System.out.println(Arrays.toString(findBestSequence(4, 6, 7, 5, 3)));
//        System.out.println(Arrays.toString(findBestSequence(5, 7, 8, 6, 4)));
//        System.out.println(Arrays.toString(findBestSequence(6, 8, 9, 7, 5)));
//        System.out.println(Arrays.toString(findBestSequence(7, 9, 10, 8, 6)));
//        System.out.println(Arrays.toString(findBestSequence(8, 10, 11, 9, 7)));
//        System.out.println(Arrays.toString(findBestSequence(9, 11, 12, 10, 8)));
//        System.out.println(Arrays.toString(findBestSequence(10, 12, 13, 11, 9)));
//        System.out.println(Arrays.toString(findBestSequence(11, 13, 14, 12, 10)));
        System.out.println(Arrays.toString(findBestSequence(13, 15, 16, 14, 12)));
//        System.out.println(Arrays.toString(findBestSequence(2, 4, 6, 7, 5, 3)));
//        System.out.println(Arrays.toString(findBestSequence(2, 4, 6, 5, 3, 7)));
//        System.out.println(Arrays.toString(findBestSequence(6, 2, 4, 5, 3, 7)));
//        System.out.println(compute(new int[]{6, 2, 4, 5, 3, 7}, 2, 4, 3, 5, 6, 7));
//        System.out.println(compute(new int[]{6, 2, 4, 5, 3, 7}, 2, 3, 4, 5, 6, 7));
//        System.out.println(Arrays.toString(findBestSequence(4, 5, 6, 7, 3, 2)));
//        System.out.println(Arrays.toString(findBestSequence(4, 6, 7, 8, 3, 2, 5)));
        System.out.println(Arrays.toString(findBestSequence(5, 7, 8, 9, 2, 3, 6, 4)));
//        System.out.println(compute(new int[]{5, 7, 8, 9, 2, 3, 6, 4}, 8, 7, 2, 3, 6, 9, 4, 5));
//        System.out.println(compute(new int[]{5, 7, 8, 9, 2, 3, 6, 4}, 8, 2, 3, 9, 7, 6, 4, 5));
//        System.out.println(Arrays.toString(findBestSequence(5, 1, 7, 8, 9, 2, 3, 6, 4)));
//        System.out.println(Arrays.toString(findBestSequence(5, 8, 9, 7, 2, 3, 6, 4)));
        System.out.println(Arrays.toString(findBestSequence(5, 7, 9, 6, 4)));
        System.out.println(compute(new int[]{3, 5, 6, 4, 2}, 5, 4, 6, 2, 3));
        System.out.println(compute(new int[]{3, 5, 6, 4, 2}, 6, 5, 4, 2, 3));
        System.out.println(Arrays.toString(findBestSequence(15, 25, 30, 20, 10)));
    }

    @Test
    public void test_25() {
        assertThat(solve(7, 9, 8, 0, 7, 1, 3, 5, 5, 2, 3, 3)).isEqualTo(1717);
    }

    @Test
    public void test_31() {
        assertThat(solve(8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2)).isEqualTo(3446);
    }

    @Test
    public void test_33() {
        assertThat(solve("[8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2,5,5]")).isEqualTo(3830);
    }

    @Test
    public void test_34() {
        assertThat(solve("[8,3,4,3,5,0,5,6,6,2,8,5,6,2,3,8,3,5,1,0,2]")).isEqualTo(3266);
    }
}
