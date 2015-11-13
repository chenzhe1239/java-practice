package com.janosgyerik.practice.oj.codingame.easy.mime;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        in.nextLine();

        Map<String, String> extmap = new HashMap<String, String>();
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            String[] parts = line.split(" ");
            extmap.put(parts[0].toLowerCase(), parts[1]);
        }

        for (int i = 0; i < q; ++i) {
            String filename = in.nextLine();
            String ext = FileUtils.getExt(filename);
            String mime = extmap.get(ext);
            if (mime == null) {
                mime = "UNKNOWN";
            }
            System.out.println(mime);
        }
    }
}

class FileUtils {
    public static String getExt(String filename) {
        String[] parts = filename.split("\\.", -1);
        if (parts.length > 1) {
            return parts[parts.length - 1].toLowerCase();
        }
        return "";
    }
}