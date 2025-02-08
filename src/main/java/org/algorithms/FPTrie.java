package org.algorithms;

import java.util.*;

class FPTrie {
    private final FPNode root = new FPNode(null, -1, null);
    private final Map<String, List<FPNode>> byId = new HashMap<>();
    private final FrequentPatternSet fps;

    public FPTrie(int minSupport, Collection<Transaction> transactions) {
        fps = FrequentPatternSet.of(minSupport, transactions);
        for (Transaction t : transactions) {
            FPNode parent = root;
            List<String> path = t.orderedItems(fps);
            for (String item : path) {
                FPNode node = parent.children.get(item);
                if (node == null) {
                    node = new FPNode(item, 0, parent);
                    List<FPNode> nodes = byId.getOrDefault(item, new ArrayList<>());
                    nodes.add(node);
                    byId.put(item, nodes);
                }
                node.count++;
                parent = node;
            }
        }
    }

    public List<ItemWithConditionalPatternBases> getConditionalPatternBase() {
        List<ItemWithConditionalPatternBases> result = new ArrayList<>();
        for (Map.Entry<String, List<FPNode>> entry : byId.entrySet()) {
            String item = entry.getKey();
            List<FPNode> nodes = entry.getValue();
            List<ConditionalPatternBase> patternBases = new ArrayList<>();
            for (FPNode node : nodes) {
                List<String> path = node.getPathToRoot();
                List<String> sortedItemsInPath = path.stream().sorted(fps.byFrequencies()).toList();
                if (!sortedItemsInPath.isEmpty()) {
                    ConditionalPatternBase cpb = new ConditionalPatternBase(sortedItemsInPath, node.count);
                    patternBases.add(cpb);
                }
            }
            result.add(new ItemWithConditionalPatternBases(item, patternBases));
        }
        return result;
    }
}
