package org.algorithms;

import java.util.List;

public record FrequentPattern(List<String> items, int frequency) {
    @Override
    public String toString() {
        return "{" + items + ": " + frequency + "}";
    }
}
