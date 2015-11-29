package com.janosgyerik.practice.oj.leetcode.medium.CourseSchedule;

import java.util.*;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = toMap(prerequisites);
        Set<Integer> finishable = new HashSet<>();
        for (int index : map.keySet()) {
            if (!canFinish(map, new boolean[numCourses], index, finishable)) {
                return false;
            } else {
                finishable.add(index);
            }
        }
        return true;
    }

    private boolean canFinish(Map<Integer, Set<Integer>> map, boolean[] visited, int index, Set<Integer> finishable) {
        if (finishable.contains(index)) {
            return true;
        }
        if (visited[index]) {
            return false;
        }
        visited[index] = true;
        Set<Integer> prerequisites = map.get(index);
        if (prerequisites == null) {
            return true;
        }
        for (int req : prerequisites) {
            if (!canFinish(map, visited, req, finishable)) {
                return false;
            } else {
                finishable.add(req);
            }
        }
        return true;
    }

    private Map<Integer, Set<Integer>> toMap(int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] pair : prerequisites) {
            Set<Integer> neighbors = map.get(pair[0]);
            if (neighbors == null) {
                neighbors = new HashSet<>();
                map.put(pair[0], neighbors);
            }
            neighbors.add(pair[1]);
        }
        return map;
    }
}
