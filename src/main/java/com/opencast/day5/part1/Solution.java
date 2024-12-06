package com.opencast.day5.part1;


import com.opencast.day5.Rule;

import java.util.*;

import static java.util.stream.Collectors.*;

class Solution {
    private Solution() {}

    public static long getSumOfCorrectMiddlePages(List<String> rules, List<String> orders) {
        var ruleMap = getBeforeRulesForNumbers(rules);

        return orders.stream()
                .map(order -> order.split(","))
                .map(orderArr -> Arrays.stream(orderArr).map(Integer::parseInt).toList())
                .filter(order -> isOrderCorrect(ruleMap, order))
                .map(order -> order.get(order.size() / 2))
                .mapToLong(Integer::longValue)
                .sum();
    }

    private static Map<Integer, Set<Integer>> getBeforeRulesForNumbers(List<String> rules) {
        return rules.stream()
                .map(rule -> rule.split("\\|"))
                .map(rule -> new Rule(Integer.parseInt(rule[0]), Integer.parseInt(rule[1])))
                .collect(groupingBy(Rule::before, mapping(Rule::after, toSet())));
    }

    private static boolean isOrderCorrect(Map<Integer, Set<Integer>> rulesMap, List<Integer> printingOrder) {
        for (int i = 0; i < printingOrder.size(); i ++) {
            Set<Integer> beforeRule = rulesMap.getOrDefault(printingOrder.get(i), Collections.emptySet());

            if (!beforeRule.isEmpty() && isRuleBroken(beforeRule, i, printingOrder)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isRuleBroken(Set<Integer> rule, Integer position, List<Integer> printingOrder) {
        return printingOrder.subList(0, position).stream()
                .anyMatch(rule::contains);
    }
}
