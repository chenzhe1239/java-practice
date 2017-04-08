package com.janosgyerik.practice.oj.leetcode.medium.UniqueBinarySearchTrees2.so;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.BooleanSupplier;
import java.util.stream.*;

public class BlahTest {
    @Test
    public void test_cahrs() {
        char[] chars = {'a', 'b', 'c'};
        System.out.println(new String(chars));
    }

    class Priority {
        final boolean severity;

        Priority(boolean severity) {
            this.severity = severity;
        }

        boolean getSeverity() {
            return severity;
        }

        @Override
        public String toString() {
            return Boolean.toString(severity);
        }
    }

    class CompareOrder<T extends Priority> implements Comparator<T> {
        @Override
        public int compare(Priority left, Priority right) {
            return -Boolean.compare(left.getSeverity(), right.getSeverity());
        }
    }

    @Test
    public void test_ordering() {
        int size = 5;
        PriorityBlockingQueue<Priority> q = new PriorityBlockingQueue<>(size, Collections.reverseOrder(new CompareOrder<>()));
        q.add(new Priority(true));
        q.add(new Priority(false));
        q.add(new Priority(true));

        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }

    static <L extends List<T>, T extends Number>
    void useList(List<L> list) {
        L ts = list.get(0);
        T t = ts.get(0);
    }

    static <L extends List<? extends Number>>
    void useList2(List<L> list) {
        L ts = list.get(0);
        Number t = ts.get(0);
    }

    @Test
    public void test_sort_arrays() {
        char[] chars = "hello".toCharArray();

    }

    @Test
    public void test_avg() {
        LinkedList<String> firstList= new LinkedList<String>();
        firstList.add("A");
        firstList.add("B");
        LinkedList<String> secondList= new LinkedList<String>();
        secondList.add("A");
        secondList.add("B");
        secondList.add("C");

        List<String> copy = new LinkedList<>(secondList);
        copy.removeAll(firstList);
        System.out.println(secondList);
        System.out.println(copy);
    }
}
