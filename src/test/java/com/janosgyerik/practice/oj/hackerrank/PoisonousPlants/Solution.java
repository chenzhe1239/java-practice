package com.janosgyerik.practice.oj.hackerrank.PoisonousPlants;

import java.util.*;

public class Solution {
    static int countDays(List<Integer> plants) {
        int days = 0;

        while (!plants.isEmpty()) {
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
