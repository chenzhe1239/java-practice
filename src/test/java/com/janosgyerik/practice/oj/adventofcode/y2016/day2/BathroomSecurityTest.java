package com.janosgyerik.practice.oj.adventofcode.y2016.day2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BathroomSecurityTest {
    static class Walker {
        private static final char OFF = 'X';

        private final char[][] keypad;
        private int row;
        private int col;

        public Walker() {
            keypad = new char[][] {
                    {'1', '2', '3'},
                    {'4', '5', '6'},
                    {'7', '8', '9'}
            };
            row = 1;
            col = 1;
        }

        public Walker(char[][] keypad, int row, int col) {
            this.keypad = keypad;
            this.row = row;
            this.col = col;
        }

        boolean isLegal(int row, int col) {
            return 0 <= row && row < keypad.length && 0 <= col && col < keypad[row].length
                    && keypad[row][col] != OFF;
        }

        void up() {
            if (isLegal(row - 1, col)) {
                row--;
            }
        }

        void down() {
            if (isLegal(row + 1, col)) {
                row++;
            }
        }

        void left() {
            if (isLegal(row, col - 1)) {
                col--;
            }
        }

        void right() {
            if (isLegal(row, col + 1)) {
                col++;
            }
        }

        char current() {
            return keypad[row][col];
        }

        Walker move(String steps) {
            for (char c : steps.toCharArray()) {
                switch (c) {
                    case 'U': up(); break;
                    case 'D': down(); break;
                    case 'L': left(); break;
                    case 'R': right(); break;
                    default: throw new IllegalStateException("invalid input");
                }
            }
            return this;
        }
    }

    @Test
    public void should_get_1_for_ULL() {
        assertThat(new Walker().move("ULL").current()).isEqualTo('1');
    }

    @Test
    public void should_get_9_for_ULL_RRDDD() {
        assertThat(new Walker().move("ULL").move("RRDDD").current()).isEqualTo('9');
    }

    @Test
    public void should_get_8_for_ULL_RRDDD_LURDL() {
        assertThat(new Walker().move("ULL").move("RRDDD").move("LURDL").current()).isEqualTo('8');
    }

    @Test
    public void should_get_5_for_ULL_RRDDD_LURDL_UUUUD() {
        assertThat(new Walker().move("ULL").move("RRDDD").move("LURDL").move("UUUUD").current()).isEqualTo('5');
    }

    String finalInput = (
            "UDRLRRRUULUUDULRULUDRDRURLLDUUDURLUUUDRRRLUUDRUUDDDRRRLRURLLLDDDRDDRUDDULUULDDUDRUUUDLRLLRLDUDUUUUDLDULLLDRLRLRULDDDDDLULURUDURDDLLRDLUDRRULDURDDLUDLLRRUDRUDDDLLURULRDDDRDRRLLUUDDLLLLRLRUULRDRURRRLLLLDULDDLRRRRUDRDULLLDDRRRDLRLRRRLDRULDUDDLDLUULRDDULRDRURRURLDULRUUDUUURDRLDDDURLDURLDUDURRLLLLRDDLDRUURURRRRDRRDLUULLURRDLLLDLDUUUDRDRULULRULUUDDULDUURRLRLRRDULDULDRUUDLLUDLLLLUDDULDLLDLLURLLLRUDRDLRUDLULDLLLUDRLRLUDLDRDURDDULDURLLRRRDUUDLRDDRUUDLUURLDRRRRRLDDUUDRURUDLLLRRULLRLDRUURRRRRLRLLUDDRLUDRRDUDUUUDRUDULRRULRDRRRDDRLUUUDRLLURURRLLDUDRUURDLRURLLRDUDUUDLLLUULLRULRLDLRDDDU\n" +
            "DRRRDRUDRLDUUDLLLRLULLLUURLLRLDRLURDRDRDRLDUUULDRDDLDDDURURUDRUUURDRDURLRLUDRRRDURDRRRDULLRDRRLUUUURLRUULRRDUDDDDUURLDULUDLLLRULUDUURRDUULRRDDURLURRUDRDRLDLRLLULULURLRDLRRRUUURDDUUURDRDRUURUDLULDRDDULLLLLRLRLLUDDLULLUDDLRLRDLDULURDUDULRDDRLUDUUDUDRLLDRRLLDULLRLDURUDRLRRRDULUUUULRRLUDDDLDUUDULLUUURDRLLULRLDLLUUDLLUULUULUDLRRDDRLUUULDDRULDRLURUURDLURDDRULLLLDUDULUDURRDRLDDRRLRURLLRLLLLDURDLUULDLDDLULLLRDRRRDLLLUUDDDLDRRLUUUUUULDRULLLDUDLDLURLDUDULRRRULDLRRDRUUUUUURRDRUURLDDURDUURURULULLURLLLLUURDUDRRLRRLRLRRRRRULLDLLLRURRDULLDLLULLRDUULDUDUDULDURLRDLDRUUURLLDLLUUDURURUD\n" +
            "UDUUUUURUDLLLRRRDRDRUDDRLLDRRLDRLLUURRULUULULRLLRUDDRLDRLUURDUDLURUULLLULLRRRULRLURRDDULLULULRUDDDUURDRLUDUURRRRUUULLRULLLDLURUDLDDLLRRRULDLLUURDRRRDRDURURLRUDLDLURDDRLLLUUDRUULLDLLLLUUDRRURLDDUDULUDLDURDLURUURDUUUURDLLLRUUURDUUUDLDUDDLUDDUDUDUDLDUDUUULDULUURDDLRRRULLUDRRDLUDULDURUURULLLLUDDDLURURLRLRDLRULRLULURRLLRDUDUDRULLRULRUDLURUDLLDUDLRDRLRDURURRULLDDLRLDDRLRDRRDLRDDLLLLDUURRULLRLLDDLDLURLRLLDULRURRRRDULRLRURURRULULDUURRDLURRDDLDLLLRULRLLURLRLLDDLRUDDDULDLDLRLURRULRRLULUDLDUDUDDLLUURDDDLULURRULDRRDDDUUURLLDRDURUDRUDLLDRUD\n" +
            "ULRDULURRDDLULLDDLDDDRLDUURDLLDRRRDLLURDRUDDLDURUDRULRULRULULUULLLLDRLRLDRLLLLLRLRRLRLRRRDDULRRLUDLURLLRLLURDDRRDRUUUDLDLDRRRUDLRUDDRURRDUUUDUUULRLDDRDRDRULRLLDLDDLLRLUDLLLLUURLDLRUDRLRDRDRLRULRDDURRLRUDLRLRLDRUDURLRDLDULLUUULDRLRDDRDUDLLRUDDUDURRRRDLDURRUURDUULLDLRDUDDLUDDDRRRULRLULDRLDDRUURURLRRRURDURDRULLUUDURUDRDRLDLURDDDUDDURUDLRULULURRUULDRLDULRRRRDUULLRRRRLUDLRDDRLRUDLURRRDRDRLLLULLUULRDULRDLDUURRDULLRULRLRRURDDLDLLRUUDLRLDLRUUDLDDLLULDLUURRRLRDULRLRLDRLDUDURRRLLRUUDLUURRDLDDULDLULUUUUDRRULLLLLLUULDRULDLRUDDDRDRDDURUURLURRDLDDRUURULLULUUUDDLRDULDDLULDUDRU\n" +
            "LRLRLRLLLRRLUULDDUUUURDULLLRURLDLDRURRRUUDDDULURDRRDURLRLUDLLULDRULLRRRDUUDDRDRULLDDULLLUURDLRLRUURRRLRDLDUDLLRLLURLRLLLDDDULUDUDRDLRRLUDDLRDDURRDRDUUULLUURURLRRDUURLRDLLUDURLRDRLURUURDRLULLUUUURRDDULDDDRULURUULLUDDDDLRURDLLDRURDUDRRLRLDLRRDDRRDDRUDRDLUDDDLUDLUDLRUDDUDRUDLLRURDLRUULRUURULUURLRDULDLDLLRDRDUDDDULRLDDDRDUDDRRRLRRLLRRRUUURRLDLLDRRDLULUUURUDLULDULLLDLULRLRDLDDDDDDDLRDRDUDLDLRLUDRRDRRDRUURDUDLDDLUDDDDDDRUURURUURLURLDULUDDLDDLRUUUULRDRLUDLDDLLLRLLDRRULULRLRDURRRLDDRDDRLU"
    );
    String sampleInput = (
            "ULL\n" +
            "RRDDD\n" +
            "LURDL\n" +
            "UUUUD"
            );

    String solve(Walker walker, String input) {
        String code = "";
        for (String line : input.split("\\n")) {
            code += walker.move(line).current();
        }
        return code;
    }

    @Test
    public void solvePart1_should_give_56855() {
        assertThat(solve(new Walker(), finalInput)).isEqualTo("56855");
    }

    @Test
    public void solvePart2_should_give_B3C27() {
        char[][] keypad = {
                "XX1XX".toCharArray(),
                "X234X".toCharArray(),
                "56789".toCharArray(),
                "XABCX".toCharArray(),
                "XXDXX".toCharArray(),
        };
        assertThat(solve(new Walker(keypad, 2, 0), sampleInput)).isEqualTo("5DB3");
        assertThat(solve(new Walker(keypad, 2, 0), finalInput)).isEqualTo("B3C27");
    }
}
