package com.janosgyerik.practice.oj.leetcode.medium.SimplifyPath;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimplifyPathTest {

    private final Solution solution = new Solution();

    private String solve(String path) {
        return solution.simplifyPath(path);
    }

    @Test
    public void test_drop_trailing_slash() {
        assertEquals("/home", solve("/home/"));
    }

    @Test
    public void test_skip_dotslash() {
        assertEquals("/a/b", solve("/a/./b"));
    }

    @Test
    public void test_skip_level() {
        assertEquals("/b", solve("/a/../b"));
    }

    @Test
    public void test_skip_single_level_to_root() {
        assertEquals("/", solve("/a/.."));
    }

    @Test
    public void test_skip_levels_to_root() {
        assertEquals("/", solve("/a/b/../.."));
    }

    @Test
    public void test_skip_levels_and_dotslash_to_root() {
        assertEquals("/", solve("/a/./b/../.."));
    }

    @Test
    public void test_drop_excess_slashes() {
        assertEquals("/a/b", solve("//a//b"));
    }

    @Test
    public void test_empty() {
        assertEquals("", solve(""));
    }

    @Test
    public void test_root() {
        assertEquals("/", solve("/"));
    }

    @Test
    public void test_drop_excess_up() {
        assertEquals("/", solve("/../"));
    }

    @Test
    public void test_slash_dot_to_root() {
        assertEquals("/", solve("/."));
    }

    @Test
    public void test_slash_2dots_to_root() {
        assertEquals("/", solve("/.."));
    }

    @Test
    public void test_slash_3dots_to_same() {
        assertEquals("/...", solve("/..."));
    }

    @Test
    public void test_abc_slash_3dots_to_same() {
        assertEquals("/abc/...", solve("/abc/..."));
    }

    @Test
    public void test_complex_example_1() {
        assertEquals("/e/f/g", solve("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
    }

    @Test
    public void test_complex_example_2() {
        assertEquals("/E", solve("/.././em/jl///../.././../E/"));
    }

    @Test
    public void test_tle() {
        assertEquals(
                "/ijNTjQBo/xuciGhUyByFVOjJhgED/GzdfOuUGxTQqIXDfNovH/KR/yjyrmUMj/PNyTErUEEgv/aEtXq/jnt/taTYTZyGe/SrhzyE/PnAfAbpxDNflwoxxkbxw/llNFbmSHXBGhxbkW/TJ/ZqDn/PRPDEfWqerQ/APIGHgzvxZhomgzZwdyN/vpASftpSygLBFBQ/QnZEK/eRCrEjYxoei/cMCBP/twvs/YrfBmNhnkBu/kmjyEtltCQRu/Mc/vMS/pLmEee/hAKyLPHNOwtMCgqiGxi/ylo/DfgJbaqITUVd/gLiVZuPovAsGZEC/uJAooe/lGyQYSHobicwrgfggC/iKKHALc/EHBdD/IgtPWaKtPhgLaB/VuIFJNhohuS/wFJaBzcttfEgdwHMG/zFyeBhwtmmbeyySASBnc/sSgEroqdbVqkaf/aIbGiHnUwJp/SLuiTuJNwXWRebgvx",
                solve("///YRoHmKzDPT/../ijNTjQBo/./xuciGhUyByFVOjJhgED/GzdfOuUGxTQqIXDfNovH///WSXtBsbLhnSqVayh/./../KR/yjyrmUMj/PNyTErUEEgv/aEtXq/./jnt/././taTYTZyGe/./SrhzyE/.///PnAfAbpxDNflwoxxkbxw/./llNFbmSHXBGhxbkW/./z/../////TJ/././ZqDn///vpvmrVpzow/./..///PRPDEfWqerQ/./vBUYYSZaEkFSZhA/../APIGHgzvxZhomgzZwdyN/////vpASftpSygLBFBQ/rAlyO/AnrUm/../../QnZEK/./yGdKhuQgakHoRSVF/ecdONvPHzhrn/./../../eRCrEjYxoei/./////./cMCBP/twvs/YrfBmNhnkBu/eVjaFLyMIuskUr/./..///kmjyEtltCQRu/Mc/vMS/ZLwuPBOuKbMtCfAn/../pLmEee/hAKyLPHNOwtMCgqiGxi/kdXx/..///ylo/DfgJbaqITUVd/gLiVZuPovAsGZEC/uJAooe///lGyQYSHobicwrgfggC/iKKHALc/EHBdD/IgtPWaKtPhgLaB/VuIFJNhohuS/DZiLhIeFHK/aDiLPzMtTtAUYN///bXlruYQJiDmXCkNXZf///../D/../../././pxKtpC/cereDCYQ/liMEkhdEOx/../../../.././wFJaBzcttfEgdwHMG/LgfgBmBtITnrmQIK/../MzqcQbVhblBBpgjUKV/../zFyeBhwtmmbeyySASBnc///sSgEroqdbVqkaf///////aIbGiHnUwJp/pr/VEAbqlGThQydflgdJC///..///../mDmUlhARJLSgpR/.././SLuiTuJNwXWRebgvx/tEADMNKMUby/../YqjnVbFyTpJsSTUW/.."));
    }
}
