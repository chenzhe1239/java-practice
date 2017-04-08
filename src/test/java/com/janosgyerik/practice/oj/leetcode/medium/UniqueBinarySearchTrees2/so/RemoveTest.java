package com.janosgyerik.practice.oj.leetcode.medium.UniqueBinarySearchTrees2.so;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class RemoveTest {
    @Test
    public void test_example_1() {
        assertThat(toList(deleteDuplicates(create(1, 2, 3, 3, 4, 4, 5)))).isEqualTo(Arrays.asList(1, 2, 5));
    }

    @Test
    public void test_example_2() {
        assertThat(toList(deleteDuplicates(create(1, 1, 1, 2, 3, 4, 5, 6)))).isEqualTo(Arrays.asList(2, 3, 4, 5, 6));
    }

    ListNode create(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        for (int value : values) {
            node.next = new ListNode(value);
            node = node.next;
        }
        return dummy.next;
    }

    List<Integer> toList(ListNode root) {
        List<Integer> list = new ArrayList<>();
        ListNode node = root;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode post = head.next;
        ListNode curr = head;
        ListNode dummy = new ListNode(head.val - 1);  // make sure dummy node value is different from the head
        dummy.next = head;
        ListNode prev = dummy;

        while (post != null) {
            System.out.println("prev:" + toList(prev));
            System.out.println("curr:" + toList(curr));
            System.out.println("post:" + toList(post));
            System.out.println();

            if (prev.next.val != curr.val || prev.next.val != post.val) {
                if (prev.next.val == curr.val) {
                    prev.next = curr.next;
                } else {
                    prev = prev.next;
                }
            }
//            if (prev.next.val != curr.val || prev.next.val != post.val) {
//                if (prev.next.val == curr.val) {
//                    prev.next = curr.next;
//                } else {
//                    prev = prev.next;
//                }
//            }
            curr = curr.next;
            post = post.next;
        }

        return dummy.next;
    }

    @Test
    public void test_divide() {
        Scanner sc = new Scanner("hello");
        System.out.println("Welcome to the Grand Quadratic Calculator ! ");
        double a, b, c;
        try {
            System.out.print("Enter value of a = ");
            a = sc.nextDouble();
            System.out.print("Enter value of b = ");
            b = sc.nextDouble();
        } catch (InputMismatchException e) {
            System.out.print("Invalid Input. \nGood Bye.");
        }
    }


    @Test
    public void test_comparable() {
        func(getString());
    }

    private static void func(Comparable... input) {
        System.out.println(Arrays.toString(input));
    }

    private static void func(Map<?, ?> m) {
        System.out.println(m);
    }

    private static <T extends Comparable> T getString() {
        return (T) "aaa";
    }

    @Test
    public void test_last() {
        for (int i = 0; i < 101; i++) {
            if (i % 7 != 0 && i % 10 != 7) {
                System.out.println(i);
            }
        }
    }
}
