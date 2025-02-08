package org.algorithms;

import java.util.*;

class FPNode {
    public String id;
    public int count;
    public FPNode parent;
    public Map<String, FPNode> children;

    public FPNode(String id, int count, FPNode parent) {
        this.id = id;
        this.count = count;
        this.parent = parent;
        this.children = new HashMap<>();
        if (parent != null) {
            parent.children.put(id, this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FPNode fpNode = (FPNode) o;
        return count == fpNode.count && Objects.equals(id, fpNode.id) && Objects.equals(parent, fpNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, parent);
    }

    public List<String> getPathToRoot() {
        if (parent == null || parent.id == null) {
            return Collections.emptyList();
        }
        List<String> path = new ArrayList<>();
        path.add(parent.id);
        path.addAll(parent.getPathToRoot());
        return path;
    }

    @Override
    public String toString() {
        if (parent == null) {
            return "ROOT";
        }
        return id + " [" + count + "]/" + String.join("/", getPathToRoot());
    }
}
