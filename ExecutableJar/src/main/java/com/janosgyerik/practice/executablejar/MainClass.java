package com.janosgyerik.practice.executablejar;

import java.io.File;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("hello world");
        File jarFile = new File(MainClass.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println(jarFile.getAbsolutePath());
    }
}
