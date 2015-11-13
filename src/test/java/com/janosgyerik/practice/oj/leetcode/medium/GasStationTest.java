package com.janosgyerik.practice.oj.leetcode.medium;

public class GasStationTest {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startIndex = findMostPromisingStart(gas, cost);
        for (int i = 0; i < gas.length; ++i) {
            if (canCompleteCircuitFrom(gas, cost, i + startIndex)) {
                return convertIndex(gas.length, i + startIndex);
            }
        }
        return -1;
    }

    int findMostPromisingStart(int[] gas, int[] cost) {
        int biggestDiff = 0;
        int index = 0;
        for (int i = 0; i < gas.length; ++i) {
            int diff = gas[i] - cost[i];
            if (diff > biggestDiff) {
                biggestDiff = diff;
                index = i;
            } else if (diff < 0) {
                biggestDiff = 0;
            }
        }
        return index;
    }

    boolean canCompleteCircuitFrom(int[] gas, int[] cost, int startIndex) {
        int fuel = 0;
        for (int i = 0; i < gas.length; ++i) {
            int index = convertIndex(gas.length, startIndex + i);
            fuel += gas[index];
            if (fuel < cost[index]) {
                return false;
            }
            fuel -= cost[index];
        }
        return true;
    }

    int convertIndex(int length, int index) {
        return index % length;
    }
}
