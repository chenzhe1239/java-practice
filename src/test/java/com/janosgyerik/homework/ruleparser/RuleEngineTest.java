package com.janosgyerik.homework.ruleparser;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.janosgyerik.homework.ruleparser.Conditions.*;
import static org.junit.Assert.assertEquals;

public class RuleEngineTest {
    @Test
    public void should_return_empty_when_no_rules() {
        RuleEngine<Character, Integer> ruleEngine = RuleEngine.<Character, Integer>builder().build();

        FactList<Character, Integer> factList = FactList.<Character, Integer>builder()
                .add('A', 1)
                .add('B', 2)
                .add('C', 3)
                .build();

        assertEquals(Collections.emptyMap(), ruleEngine.validate(factList));
    }

    @Test
    public void should_return_empty_when_no_facts() {
        RuleEngine<Character, Integer> ruleEngine = RuleEngine.<Character, Integer>builder()
                .add(new RuleImpl<>('A', equal(1)))
                .add(new RuleImpl<>('B', lessThan(3)))
                .add(new RuleImpl<>('C', not(equal(8))))
                .add(new RuleImpl<>('B', greaterThan(1)))
                .build();

        FactList<Character, Integer> factList = FactList.<Character, Integer>builder().build();

        assertEquals(Collections.emptyMap(), ruleEngine.validate(factList));
    }

    @Test
    public void should_return_violated_rules_when_all_violated() {
        int targetA = 2;
        int targetB = 3;
        Rule<Character, Integer> aEqual2 = new RuleImpl<>('A', equal(targetA));
        Rule<Character, Integer> bEqual3 = new RuleImpl<>('B', equal(targetB));

        RuleEngine<Character, Integer> ruleEngine = RuleEngine.<Character, Integer>builder()
                .add(aEqual2)
                .add(bEqual3)
                .build();

        FactList<Character, Integer> factList = FactList.<Character, Integer>builder()
                .add('A', targetA + 1)
                .add('B', targetB + 1)
                .add('C', 3)
                .build();

        Map<Character, List<Rule<Character, Integer>>> violations = new HashMap<>();
        violations.put('A', Collections.singletonList(aEqual2));
        violations.put('B', Collections.singletonList(bEqual3));
        assertEquals(violations, ruleEngine.validate(factList));
    }

    @Test
    public void should_return_violated_rules_when_some_violated() {
        int targetA = 2;
        int targetB = 3;
        Rule<Character, Integer> aEqual2 = new RuleImpl<>('A', equal(targetA));
        Rule<Character, Integer> bEqual3 = new RuleImpl<>('B', equal(targetB));

        RuleEngine<Character, Integer> ruleEngine = RuleEngine.<Character, Integer>builder()
                .add(aEqual2)
                .add(bEqual3)
                .build();

        FactList<Character, Integer> factList = FactList.<Character, Integer>builder()
                .add('A', targetA)
                .add('B', targetB + 1)
                .add('C', 3)
                .build();

        Map<Character, List<Rule<Character, Integer>>> violations = new HashMap<>();
        violations.put('B', Collections.singletonList(bEqual3));
        assertEquals(violations, ruleEngine.validate(factList));
    }

    @Test
    public void should_return_empty_when_none_violated() {
        int targetA = 2;
        int targetB = 3;
        Rule<Character, Integer> aEqual2 = new RuleImpl<>('A', equal(targetA));
        Rule<Character, Integer> bEqual3 = new RuleImpl<>('B', equal(targetB));

        RuleEngine<Character, Integer> ruleEngine = RuleEngine.<Character, Integer>builder()
                .add(aEqual2)
                .add(bEqual3)
                .build();

        FactList<Character, Integer> factList = FactList.<Character, Integer>builder()
                .add('A', targetA)
                .add('B', targetB)
                .add('C', 3)
                .build();

        assertEquals(Collections.emptyMap(), ruleEngine.validate(factList));
    }

    @Test
    public void should_return_unique_1_violation_when_violated_multiple_times() {
        int targetA = 2;
        Rule<Character, Integer> aEqual2 = new RuleImpl<>('A', equal(targetA));

        RuleEngine<Character, Integer> ruleEngine = RuleEngine.<Character, Integer>builder()
                .add(aEqual2)
                .build();

        FactList<Character, Integer> factList = FactList.<Character, Integer>builder()
                .add('A', targetA + 1)
                .add('A', targetA + 2)
                .add('A', targetA + 3)
                .add('B', 3)
                .add('C', 3)
                .build();

        assertEquals(Collections.singletonMap('A', Collections.singletonList(aEqual2)), ruleEngine.validate(factList));
    }

    @Test
    public void should_return_violations_when_violated_and_matching_both_exist() {
        int targetA = 2;
        Rule<Character, Integer> aEqual2 = new RuleImpl<>('A', equal(targetA));

        RuleEngine<Character, Integer> ruleEngine = RuleEngine.<Character, Integer>builder()
                .add(aEqual2)
                .build();

        FactList<Character, Integer> factList = FactList.<Character, Integer>builder()
                .add('A', targetA + 1)
                .add('A', targetA)
                .build();

        assertEquals(Collections.singletonMap('A', Collections.singletonList(aEqual2)), ruleEngine.validate(factList));
    }
}
