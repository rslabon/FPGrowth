package org.algorithms;

import java.util.*;
import java.util.stream.Collectors;

record ConditionalFrequentPattern(String item, List<String> commonItems, int frequency) {

    public Collection<FrequentPattern> getFrequentPatterns(int minPattern, int maxPattern) {
        if (commonItems.isEmpty()) {
            return Collections.emptySet();
        }
        Set<Set<String>> combinations = combinations(new HashSet<>(commonItems));
        return combinations.stream()
                .map(c -> {
                    Set<String> withItem = new HashSet<>(c);
                    withItem.add(item);
                    return withItem;
                })
                .filter(c -> c.size() >= minPattern && c.size() <= maxPattern)
                .map(c -> new FrequentPattern(c.stream().toList(), frequency))
                .collect(Collectors.toSet());
    }

    private Set<Set<String>> combinations(Set<String> items) {
        if (items.isEmpty()) {
            return Collections.emptySet();
        }
        Set<Set<String>> result = new HashSet<>();
        result.add(items);
        for (String item : items) {
            Set<String> others = new HashSet<>(items);
            others.remove(item);
            result.addAll(combinations(others));
        }
        return result;
    }

    @Override
    public String toString() {
        return "{" + commonItems + ": " + frequency + "}";
    }
}
