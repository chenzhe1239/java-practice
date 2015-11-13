package com.janosgyerik.practice.oj.codingame.medium.lander;

import org.junit.Assert;
import org.junit.Test;

public class Lander2Test {
    public int getAngle(int targetX1, int targetX2, int x, int y, int hs, int vs, int r, int p) {
        int edge = - (targetX2 - targetX1) / 4;
        int targetR, targetP = 0;
        if (x < targetX1) {
            targetR = reduceR(r);
            targetP = increaseP(p, 3);
        } else if (x > targetX2) {
            targetR = increaseR(r);
            targetP = increaseP(p, 3);
        } else if (x < targetX1 + edge) {
            targetR = reduceR(r);
            targetP = reduceP(p);
        } else if (x > targetX2 - edge) {
            targetR = increaseR(r);
            targetP = reduceP(p);
        } else {
            targetR = 0;
            if (vs < -30) {
                targetP = increaseP(p);
            } else if (vs > -10) {
                targetP = reduceP(p);
            }
        }
        System.out.println(targetR + " " + targetP);
        return targetR;
    }

    public int getPower(int targetX1, int targetX2, int x, int y, int hs, int vs, int r, int p) {
        return 0;
    }

    private int increaseP(int p, int max) {
        return Math.min(max, p + 1);
    }
    private int increaseP(int p) {
        return increaseP(p, 4);
    }
    private int reduceP(int p) {
        return Math.max(0, p - 1);
    }
    private int increaseR(int r) {
        return Math.min(90, r + 15);
    }
    private int reduceR(int r) {
        return Math.max(-90, r - 15);
    }

    @Test
    public void testOvershootingToRight() {
        int targetx = 5000;
        int x = 3000;
        int vs = 10;
        // v = d / t
        // need to reduce vs to 0+ before reaching targetX1
        // distance to target is given
        // current speed is given
        // can calculate the clicks to target
        // the less clicks, the faster we need to decelerate
        // will we overshoot at current speed?
        //  if yes, then adjust in opposite direction
        // will we undershoot at current speed?
        // target the middle, use quadrants for deciding counter-action
//        Assert.assertTrue(isOvershootingToRight(targetx, x, vs));
    }

    @Test
    public void testTurnRightIfTooFar() {

    }

    @Test
    public void testTurnLeftIfOvershooting() {

    }
}
