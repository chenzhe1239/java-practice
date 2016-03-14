package com.janosgyerik.homework.ruleparser;

public class Fact<V> {
    private final Object key;
    private final V value;

    public Fact(Object key, V value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("(%s %s)", key, value);
    }
}
