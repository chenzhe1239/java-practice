package com.janosgyerik.practice.oj.leetcode.medium.SimplifyPath;

public class Solution {
    public String simplifyPath(String path) {
        String work = path.replaceAll("//+", "/");

        work = replaceAllRecursively(work, "/\\./", "/");
        work = replaceAllRecursively(work, "[^/]+/\\.\\.(/|$)", "");
        return work.replaceAll("^/(\\.\\./)+", "/")
                .replaceAll("/\\.\\.?$", "/")
                .replaceAll("(.)/+$", "$1");
    }

    private String replaceAllRecursively(String path, String regex, String replacement) {
        String work = path;
        while (true) {
            int prevlen = work.length();
            work = work.replaceFirst(regex, replacement);
            if (prevlen == work.length()) {
                break;
            }
        }
        return work;
    }
}
