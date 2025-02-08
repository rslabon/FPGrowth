package org.algorithms;

import java.util.*;
import java.util.stream.Collectors;


public class FPGrowth {

    /**
     * Find pattern (group) of items in given data sets
     *
     * @param minSupport   min frequency of item in all transactions to be in a pattern
     * @param minPattern   min size of pattern
     * @param maxPattern   max size of pattern
     * @param transactions data sets of items to search for patterns
     * @return patterns
     */
    public static Set<FrequentPattern> findPatterns(int minSupport,
                                                    int minPattern,
                                                    int maxPattern,
                                                    List<Transaction> transactions) {
        FPTrie fpTrie = new FPTrie(minSupport, transactions);
        return fpTrie.getConditionalPatternBase().stream()
                .flatMap(b -> b.getConditionalFrequentPatternTree()
                        .getFrequentPatterns(minPattern, maxPattern).stream())
                .collect(Collectors.toSet());
    }
}
