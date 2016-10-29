package com.janosgyerik.practice.oj.hackerrank.PoisonousPlants;

import java.util.*;

public class Solution {
    private static class ValueAndDays {
        final int value;
        final int days;

        ValueAndDays(int value, int days) {
            this.value = value;
            this.days = days;
        }
    }

    static int countDays(List<Integer> plants) {
        int maxDays = 0;
        int days = 0;

        Iterator<Integer> it = plants.iterator();
        int prev = it.next();
        Stack<ValueAndDays> mins = new Stack<>();
        mins.push(new ValueAndDays(prev, 0));
        boolean increasing = false;
        while (it.hasNext()) {
            int current = it.next();
            if (prev < current) {
                if (!increasing) {
                    days = 1;
                }
                increasing = true;
            } else {
                increasing = false;
                while (!mins.isEmpty() && current < mins.peek().value) {
                    mins.pop();
                }
                if (mins.isEmpty()) {
                    mins.push(new ValueAndDays(current, days));
                }
                if (mins.peek().value < current) {
                    days = mins.peek().days + 1;
                    mins.push(new ValueAndDays(current, days));
                } else {
                    days = 0;
                }
            }
            maxDays = Math.max(maxDays, days);
            prev = current;
            System.out.printf("%s %s\n", current, days);
        }
        return maxDays;
    }

    static int countDays4(List<Integer> plants2) {
        int[] plants = new int[plants2.size()];
        for (int i = 0; i < plants.length; i++) {
            plants[i] = plants2.get(i);
        }

        if (plants.length <= 1) return 0;
        int plantSize = plants.length, days = 0, i, lastDying = 1;
        while (true) {      // simulate as many days as needed
            // Search for first plant to die today
            for (i = lastDying; i < plantSize; ++i) {
                if (plants[i-1] < plants[i]) break;
            }
            if (i == plantSize) return days;  // no plant found to be dying
            lastDying = i;      // optimize the initial search next day
            int removed = 1;    // the one found (plants[i]) will die
            // Now search remaining plants for any other dying today and also remove all of them
            for (++i; i < plantSize; ++i) {
                if (plants[i-1] < plants[i]) {  // plant[i] dies, count+skip it.
                    ++removed;
                } else {                        // plant[i] survives, move it to last living one
                    plants[i-removed] = plants[i];
                }
            }
            plantSize -= removed;               // adjust total number of remaining plants
            ++days;                             // let's see another day
        }
    }

    static int countDays2(List<Integer> plants) {
        int days = 0;

        while (true) {
            boolean anyRemoved = false;

            Iterator<Integer> it = plants.iterator();
            int prev = it.next();

            while (it.hasNext()) {
                int current = it.next();
                if (current > prev) {
                    it.remove();
                    anyRemoved = true;
                }
                prev = current;
            }

            if (!anyRemoved) {
                break;
            }
            days++;
        }

        return days;
    }

    static int countDays(Scanner scanner) {
        int num = scanner.nextInt();

        List<Integer> plants = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            plants.add(scanner.nextInt());
        }

        return countDays(plants);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(countDays(scanner));
    }
}
