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
        for (ConditionalPatternBase base : bases) {
            for (Set<String> combination : Combination.of(new HashSet<>(base.path()))) {
                int totalFreqency = base.frequency();
                for (ConditionalPatternBase other : bases) {
                    if (!base.equals(other) && new HashSet<>(other.path()).containsAll(combination)) {
                        totalFreqency += other.frequency();
                    }
                }
                if (!combination.isEmpty() && totalFreqency >= minSupport) {
                    result.add(new ConditionalFrequentPattern(item, combination.stream().sorted().toList(), totalFreqency));
                }
            }
        }
        return result;
    }
}
