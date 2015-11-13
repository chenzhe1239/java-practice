package com.janosgyerik.practice.oj.codingame.easy.asciiart;

import org.junit.Test;

public class AsciiArtTest {
    @Test
    public void testManhattan() {
        String text = "MANHATTAN";
        String ascii[] = new String[]{
                " #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ###  # # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   #  ### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ##  # # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #        # # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #   "
        };
        AsciiArt art = new AsciiArt();
        for (String line : art.toAsciiArt(ascii, text, 7)) {
            System.out.println(line);
        }
    }
}
