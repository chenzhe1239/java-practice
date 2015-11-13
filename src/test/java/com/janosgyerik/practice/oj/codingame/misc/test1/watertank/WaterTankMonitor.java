package com.janosgyerik.practice.oj.codingame.misc.test1.watertank;

class WaterTank {
    boolean empty;

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}

class WaterTankMonitor {

    WaterTank tank;

    WaterTankMonitor(WaterTank tank) {
        this.tank = tank;
    }

    synchronized void empty() throws InterruptedException {
        while (tank.isEmpty()) {
            wait();
        }

        tank.setEmpty(true);
        notifyAll();
    }

    synchronized void fill() throws InterruptedException {
        while (!tank.isEmpty()) {
            wait();
        }

        tank.setEmpty(false);
        notifyAll();
    }
}