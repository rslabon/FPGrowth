package org.algorithms;

import java.util.List;

public record Transaction(List<String> items) {
    public List<String> orderedItems(FrequentPatternSet fps) {
        return items.stream()
                .filter(fps::isSupported)
                .sorted(fps.byFrequencies())
                .distinct()
                .toList();
    }
}
