package com.janosgyerik.homework.ruleparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactList<V> implements Iterable<Fact<V>> {

    private final List<Fact<V>> facts;

    public FactList(List<Fact<V>> facts) {
        this.facts = facts;
    }

    public static <V> Builder<V> builder() {
        return new Builder<>();
    }

    @Override
    public Iterator<Fact<V>> iterator() {
        return facts.iterator();
    }

    public static class Builder<V> {

        private final List<Fact<V>> facts = new ArrayList<>();

        public Builder<V> add(Object key, V value) {
            facts.add(new Fact<>(key, value));
            return this;
        }

        public FactList<V> build() {
            return new FactList<>(facts);
        }
    }
}
