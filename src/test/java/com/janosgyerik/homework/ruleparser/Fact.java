package com.janosgyerik.homework.ruleparser;

public class Fact<K, V> {
    private final K key;
    private final V value;

    public Fact(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
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
