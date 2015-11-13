package com.janosgyerik.practice.codility.easy;


import org.junit.Assert;
import org.junit.Test;

public class DomTest {

    @Test
    public void test_empty() {
        Assert.assertTrue(!hasDom(new int[0]));
    }

    @Test
    public void test_singleton_dom() {
        Assert.assertTrue(hasDom(new int[]{3}));
    }

    @Test
    public void test_doublet_dom() {
        Assert.assertTrue(hasDom(new int[]{3, 3}));
    }

    @Test
    public void test_doublet_no_dom() {
        Assert.assertTrue(!hasDom(new int[]{3, 4}));
    }

    @Test
    public void test_triplet_with_dom() {
        Assert.assertEquals(2, getDom(new int[]{1, 2, 2}));
    }

    @Test
    public void test_triplet_without_dom() {
        Assert.assertTrue(!hasDom(new int[]{1, 2, 3}));
    }

    @Test
    public void test_tuple_without_dom() {
        Assert.assertTrue(!hasDom(new int[]{1, 2, 3, 4, 5, 6}));
    }

    private int getDom(int[] arr) {
        return new Dom().getDom(arr);
    }

    private boolean hasDom(int[] arr) {
        return new Dom().hasDom(arr);
    }
}
