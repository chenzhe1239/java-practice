package com.janosgyerik.homework.ruleparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleEngine<V> {

    private final Map<Object, List<Rule<V>>> rulesMap;

    private RuleEngine(Map<Object, List<Rule<V>>> rulesMap) {
        this.rulesMap = rulesMap;
    }

    public static <V> Builder<V> builder() {
        return new Builder<>();
    }

    public Map<Object, List<Rule<V>>> validate(FactList<V> factList) {
        Map<Object, List<Rule<V>>> invalid = new HashMap<>();

        for (Fact<V> fact : factList) {
            Object key = fact.getKey();
            List<Rule<V>> rules = rulesMap.get(key);
            if (rules == null) {
                continue;
            }

            V value = fact.getValue();
            for (Rule<V> rule : rules) {
                if (!rule.getCondition().matches(value)) {
                    List<Rule<V>> list = invalid.get(key);
                    if (list == null) {
                        list = new ArrayList<>();
                        invalid.put(key, list);
                    }
                    if (!list.contains(rule)) {
                        // TODO use a Set instead
                        list.add(rule);
                    }
                }
            }
        }
        return invalid;
    }

    public static class Builder<V> {

        private final Map<Object, List<Rule<V>>> rulesMap = new HashMap<>();

        public Builder<V> add(Rule<V> rule) {
            Object key = rule.getKey();
            List<Rule<V>> rules = rulesMap.get(key);
            if (rules == null) {
                rules = new ArrayList<>();
                rulesMap.put(key, rules);
            }
            rules.add(rule);
            return this;
        }

        public RuleEngine<V> build() {
            return new RuleEngine<>(rulesMap);
        }
    }
}
