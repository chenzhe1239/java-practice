package com.janosgyerik.practice.codility.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MaxTripletProd {
    public int submit(int[] arr) {
        List<Integer> list = new ArrayList<Integer>(arr.length);
        for (int i : arr) {
            list.add(i);
        }
        Collections.sort(list);
        Iterator<Integer> ascending = list.iterator();
        int m1 = ascending.next();
        int m2 = ascending.next();
        Collections.reverse(list);
        Iterator<Integer> descending = list.iterator();
        int p1 = descending.next();
        int p2 = descending.next();
        int p3 = descending.next();
        int maxp = p1 * p2 * p3;
        int maxm = m1 * m2 * p1;
        return Math.max(maxp, maxm);
    }
}
