package com.janosgyerik.practice.oj.codingame.easy.mime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileUtilsTest {

    private String getExt(String filename) {
        return FileUtils.getExt(filename);
    }

    @Test
    public void testGetExt_uppercase() {
        assertEquals("png", getExt("file.PNG"));
    }

    @Test
    public void testGetExt_lowercase() {
        assertEquals("png", getExt("file.png"));
    }

    @Test
    public void testGetExt_mixedcase() {
        assertEquals("png", getExt("file.Png"));
    }

    @Test
    public void testGetExt_noext() {
        assertEquals("", getExt("file"));
    }

    @Test
    public void testGetExt_strangeNames() {
        assertEquals("", getExt("file."));
        assertEquals("", getExt("file.."));
        assertEquals("", getExt(".file."));
        assertEquals("file", getExt(".file"));
    }
}
