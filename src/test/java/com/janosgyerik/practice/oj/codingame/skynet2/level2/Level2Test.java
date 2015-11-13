package com.janosgyerik.practice.oj.codingame.skynet2.level2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Level2Test {

    @Test
    public void testBasicExample() {
        Map<Integer, Node> nodes = new HashMap<Integer, Node>();
        for (int i = 0; i < 10; ++i) {
            nodes.put(i, new Node(i));
        }
        new Link(nodes.get(1), nodes.get(2));
        new Link(nodes.get(1), nodes.get(3));
        new Link(nodes.get(1), nodes.get(4));
        new Link(nodes.get(2), nodes.get(5));
        new Link(nodes.get(2), nodes.get(3));
        new Link(nodes.get(3), nodes.get(4));
        new Link(nodes.get(4), nodes.get(5));
        new Link(nodes.get(6), nodes.get(5));
    }

    @Test
    public void testHarderExample() {

    }
}
