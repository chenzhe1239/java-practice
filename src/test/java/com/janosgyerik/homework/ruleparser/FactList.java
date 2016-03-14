package com.janosgyerik.homework.ruleparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactList<V> implements Iterable<Fact<V>> {

    private final List<Fact<V>> facts;

    public FactList(List<Fact<V>> facts) {
        this.facts = facts;
    }

    public static <V2> Builder<V2> builder() {
        return new Builder<>();
    }

    @Override
    public Iterator<Fact<V>> iterator() {
        return facts.iterator();
    }

    public static class Builder<V3> {

        private final List<Fact<V3>> facts = new ArrayList<>();

        public Builder<V3> add(Object key, V3 value) {
            facts.add(new Fact<>(key, value));
            return this;
        }

        public FactList<V3> build() {
            return new FactList<>(facts);
        }
    }
}
