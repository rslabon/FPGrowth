package org.algorithms;

import java.util.*;

record ItemWithConditionalPatternBases(String item, List<ConditionalPatternBase> bases) {
    @Override
    public String toString() {
        return item + ": " + bases;
    }

    public Collection<ConditionalFrequentPattern> getConditionalFrequentPattern(int minSupport) {
        if (bases.isEmpty()) {
            return Collections.emptyList();
        }
        Set<ConditionalFrequentPattern> result = new HashSet<>();
        Set<String> items = new HashSet<>();
        Map<Set<String>, Integer> pathFrequency = new HashMap<>();
        for (ConditionalPatternBase base : bases) {
            items.addAll(base.path());
            pathFrequency.put(new HashSet<>(base.path()), base.frequency());
        }

        for (Set<String> combination : Combination.of(items)) {
            int totalFreqency = pathFrequency.getOrDefault(combination, 0);
            for (ConditionalPatternBase other : bases) {
                Set<String> otherPath = new HashSet<>(other.path());
                if (!combination.equals(otherPath) && otherPath.containsAll(combination)) {
                    totalFreqency += other.frequency();
                }
            }
            if (!combination.isEmpty() && totalFreqency >= minSupport) {
                result.add(new ConditionalFrequentPattern(item, combination.stream().sorted().toList(), totalFreqency));
            }
        }
        return result;
    }
}
