package org.algorithms;

import java.util.*;

record ItemWithConditionalPatternBases(String item, List<ConditionalPatternBase> bases) {
    @Override
    public String toString() {
        return item + ": " + bases;
    }

    public ConditionalFrequentPattern getConditionalFrequentPattern() {
        if (bases.isEmpty()) {
            return new ConditionalFrequentPattern(item, Collections.emptyList(), 0);
        }
        Set<String> common = new LinkedHashSet<>(bases.getFirst().path());
        int totalFreqency = bases.getFirst().frequency();
        for (int i = 1; i < bases.size(); i++) {
            ConditionalPatternBase base = bases.get(i);
            common.retainAll(new HashSet<>(base.path()));
            totalFreqency += base.frequency();
        }
        return new ConditionalFrequentPattern(item, common.stream().sorted().toList(), totalFreqency);
    }
}
