package com.janosgyerik.homework.ruleparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactList<K, V> implements Iterable<Fact<K, V>> {

    private final List<Fact<K, V>> facts;

    public FactList(List<Fact<K, V>> facts) {
        this.facts = facts;
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    @Override
    public Iterator<Fact<K, V>> iterator() {
        return facts.iterator();
    }

    public static class Builder<K, V> {

        private final List<Fact<K, V>> facts = new ArrayList<>();

        public Builder<K, V> add(K key, V value) {
            facts.add(new Fact<>(key, value));
            return this;
        }

        public FactList<K, V> build() {
            return new FactList<>(facts);
        }
    }
}
