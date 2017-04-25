package com.janosgyerik.practice.oj.codejam.codejam2017.common;

import java.util.ArrayList;
import java.util.List;

public class Inputs {

    private final List<Input> inputs = new ArrayList<>();

    public void add(Input input) {
        inputs.add(input);
    }

    public int count() {
        return inputs.size();
    }

    public Input get(int index) {
        return inputs.get(index - 1);
    }
}
