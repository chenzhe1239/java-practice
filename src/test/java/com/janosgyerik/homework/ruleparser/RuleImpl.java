package com.janosgyerik.homework.ruleparser;

public class RuleImpl<V> implements Rule<V> {

    private final Object key;
    private final Condition<V> condition;

    public RuleImpl(Object key, Condition<V> condition) {
        this.key = key;
        this.condition = condition;
    }

    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public Condition<V> getCondition() {
        return condition;
    }
}
