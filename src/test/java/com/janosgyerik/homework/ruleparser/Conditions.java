package com.janosgyerik.homework.ruleparser;

import java.util.Comparator;

public class Conditions {

    // TODO add .equals methods for all conditions

    public static <V> Condition<V> equal(V value) {
        return value::equals;
    }

    public static <V> Condition<V> not(Condition<V> condition) {
        return value -> !condition.matches(value);
    }

    public static <V extends Comparable<V>> Condition<V> lessThan(V value) {
        return other -> other.compareTo(value) < 0;
    }

    public static <V> Condition<V> lessThan(V value, Comparator<V> comparator) {
        return other -> comparator.compare(other, value) < 0;
    }

    public static <V extends Comparable<V>> Condition<V> greaterThan(V value) {
        return other -> other.compareTo(value) > 0;
    }

    public static <V> Condition<V> greaterThan(V value, Comparator<V> comparator) {
        return other -> comparator.compare(other, value) > 0;
    }
}
