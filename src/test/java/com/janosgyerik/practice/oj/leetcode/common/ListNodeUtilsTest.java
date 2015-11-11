package com.janosgyerik.practice.oj.leetcode.common;

import org.junit.Test;

import static com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils.create;
import static org.junit.Assert.*;

public class ListNodeUtilsTest {
    @Test
    public void test_create_empty() {
        assertEquals(null, create());
    }

    @Test
    public void test_create_singleton() {
        int value = 3;
        ListNode head = create(value);
        assertEquals(value, head.val);
        assertEquals(null, head.next);
    }

    @Test
    public void test_create_with_2_items() {
        int value1 = 3;
        int value2 = 5;
        ListNode head = create(value1, value2);
        assertEquals(value1, head.val);
        assertEquals(value2, head.next.val);
        assertEquals(null, head.next.next);
    }

    @Test
    public void test_toString_empty() {
        assertEquals("", ListNodeUtils.toString(create()));
    }

    @Test
    public void test_toString_singleton() {
        int value = 3;
        ListNode head = create(value);
        assertEquals("" + value, ListNodeUtils.toString(head));
    }

    @Test
    public void test_toString_with_2_items() {
        int value1 = 3;
        int value2 = 5;
        ListNode head = create(value1, value2);
        assertEquals(String.format("%s->%s", value1, value2), ListNodeUtils.toString(head));
    }

    @Test
    public void test_toString_with_3_items() {
        int value1 = 3;
        int value2 = 5;
        int value3 = 1;
        ListNode head = create(value1, value2, value3);
        assertEquals(String.format("%s->%s->%s", value1, value2, value3), ListNodeUtils.toString(head));
    }

}
