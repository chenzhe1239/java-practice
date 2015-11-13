package com.janosgyerik.practice.codility.medium;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class CommonPrimeDivTest {

    //@Test
    public void testFactors() {
        assertEquals(Arrays.asList(3, 5), getFactors(15));
    }

    private List<Integer> getFactors(int num) {
        for (int i = 2; i < num; ++i) {

        }
        return null;
    }

    @Test
    public void testabc() {
        Node a = new Node(1, new Node(2, new Node(3, null)));
        System.out.println(a);
        Node b = new Node(4, new Node(5, new Node(6, null)));
        System.out.println(b);
        System.out.println(a.concat(a, b));
    }

    public class Node {

        final int data;
        Node next;

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node concat(Node list1, Node list2) {
            Node clone = copy(list1);
            Node iter = clone;
            while (iter.next != null) {
                iter = iter.next;
            }
            iter.next = copy(list2);
            return clone;
        }

        Node copy(Node other) {
            Node clone = new Node(other.data, null);
            Node iter = clone;
            while (other.next != null) {
                other = other.next;
                iter.next = new Node(other.data, null);
                iter = iter.next;
            }
            return clone;
        }

        @Override
        public String toString() {
            String str = "";
            Node iter = this;
            while (iter.next != null) {
                str += iter.data + " -> ";
                iter = iter.next;
            }
            str += iter.data;
            return str;
        }
    }
}
