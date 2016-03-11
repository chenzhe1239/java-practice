package com.janosgyerik.homework.ruleparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleEngine<K, V> {

    private final Map<K, List<Rule<K, V>>> rulesMap;

    private RuleEngine(Map<K, List<Rule<K, V>>> rulesMap) {
        this.rulesMap = rulesMap;
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public Map<K, List<Rule<K, V>>> validate(FactList<K, V> factList) {
        Map<K, List<Rule<K, V>>> invalid = new HashMap<>();

        for (Fact<K, V> fact : factList) {
            K key = fact.getKey();
            List<Rule<K, V>> rules = rulesMap.get(key);
            if (rules == null) {
                continue;
            }

            V value = fact.getValue();
            for (Rule<K, V> rule : rules) {
                if (!rule.getCondition().matches(value)) {
                    List<Rule<K, V>> list = invalid.get(key);
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

    public static class Builder<K, V> {

        private final Map<K, List<Rule<K, V>>> rulesMap = new HashMap<>();

        public Builder<K, V> add(Rule<K, V> rule) {
            K key = rule.getKey();
            List<Rule<K, V>> rules = rulesMap.get(key);
            if (rules == null) {
                rules = new ArrayList<>();
                rulesMap.put(key, rules);
            }
            rules.add(rule);
            return this;
        }

        public RuleEngine<K, V> build() {
            return new RuleEngine<>(rulesMap);
        }
    }
}
