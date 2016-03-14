package com.janosgyerik.homework.ruleparser;

public interface Rule<V> {

    Object getKey();

    Condition<V> getCondition();
}
