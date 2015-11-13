package com.janosgyerik.practice.oj.codingame.easy.defibrillator;

import java.util.Scanner;

class Solution {

    private static final int EARTH_RADIUS_KM = 6371;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        double lon = parseDouble(in.nextLine());
        double lat = parseDouble(in.nextLine());
        int n = in.nextInt();
        in.nextLine();

        String closest = null;
        double maxdist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            String[] parts = line.split(";");
            double lon2 = parseDouble(parts[4]);
            double lat2 = parseDouble(parts[5]);
            double distance = getDistanceApprox(lat, lon, lat2, lon2);
            if (distance < maxdist) {
                closest = parts[1];
                maxdist = distance;
            }
        }
        System.out.println(closest != null ? closest : "");
    }

    public static double parseDouble(String s) {
        return Double.parseDouble(s.replace(",", "."));
    }

    public static double getDistanceApprox(double lat, double lon, double lat2, double lon2) {
        double x = (lon - lon2) * Math.cos((lon + lon2) / 2);
        double y = lat - lat2;
        return (x * x + y * y) * EARTH_RADIUS_KM;
    }
}
