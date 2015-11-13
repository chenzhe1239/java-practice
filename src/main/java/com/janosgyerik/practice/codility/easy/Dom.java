package com.janosgyerik.practice.codility.easy;

import java.util.HashMap;
import java.util.Map;

public class Dom {
    public int getDom(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        Map<Integer, Integer> pos = new HashMap<Integer, Integer>();
        int j = 0;
        for (int i : arr) {
            Integer count = counts.get(i);
            if (count == null) {
                count = 0;
            }
            counts.put(i, count + 1);
            pos.put(i, j++);
        }
        int maxitem = arr[0];
        int maxval = 1;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxval) {
                maxitem = entry.getKey();
                maxval = entry.getValue();
            }
        }
        return maxval > arr.length / 2 ? pos.get(maxitem) : -1;
    }

    public boolean hasDom(int[] arr) {
        return getDom(arr) > -1;
    }
}
