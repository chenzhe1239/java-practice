package com.janosgyerik.homework.ruleparser;

public interface Rule<K, V> {

    K getKey();

    Condition<V> getCondition();
}
