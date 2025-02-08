package org.algorithms;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FrequentPatternSet {
    private final Map<String, Integer> frequencies = new HashMap<>();
    private final int minSupport;

    private FrequentPatternSet(int minSupport) {
        this.minSupport = minSupport;
    }

    public static FrequentPatternSet of(int minSupport, Collection<Transaction> transactions) {
        FrequentPatternSet fps = new FrequentPatternSet(minSupport);
        for (Transaction t : transactions) {
            for (String item : t.items()) {
                int frequency = fps.frequencies.getOrDefault(item, 0) + 1;
                fps.frequencies.put(item, frequency);
            }
        }
        return fps;
    }

    public boolean isSupported(String item) {
        return frequencies.getOrDefault(item, 0) >= minSupport;
    }

    @Override
    public String toString() {
        return "FrequentPatternSet{" +
                "frequencies=" + frequencies +
                ", minSupport=" + minSupport +
                '}';
    }

    public Comparator<String> byFrequencies() {
        return (i1, i2) -> {
            int cmp = frequencies.getOrDefault(i2, 0) - frequencies.getOrDefault(i1, 0);
            return cmp == 0 ? i1.compareTo(i2) : cmp;
        };
    }
}