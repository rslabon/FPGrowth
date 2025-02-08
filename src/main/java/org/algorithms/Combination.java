package org.algorithms;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Combination {
    public static Set<Set<String>> of(Set<String> items) {
        if (items.isEmpty()) {
            return Collections.emptySet();
        }
        Set<Set<String>> result = new HashSet<>();
        result.add(items);
        for (String item : items) {
            Set<String> others = new HashSet<>(items);
            others.remove(item);
            result.addAll(of(others));
        }
        return result;
    }
}
