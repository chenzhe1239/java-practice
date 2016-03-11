package com.janosgyerik.homework.ruleparser;

public interface Condition<V> {
    boolean matches(V value);
}
