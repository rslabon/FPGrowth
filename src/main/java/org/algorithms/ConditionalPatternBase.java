package org.algorithms;

import java.util.List;

record ConditionalPatternBase(List<String> path, int frequency) {
    @Override
    public String toString() {
        return "{" + String.join(",", path) + ": " + frequency + "}";
    }
}
