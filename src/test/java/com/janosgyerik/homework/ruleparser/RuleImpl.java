package com.janosgyerik.homework.ruleparser;

public class RuleImpl<K, V> implements Rule<K, V> {

    private final K key;
    private final Condition<V> condition;

    public RuleImpl(K key, Condition<V> condition) {
        this.key = key;
        this.condition = condition;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public Condition<V> getCondition() {
        return condition;
    }
}
