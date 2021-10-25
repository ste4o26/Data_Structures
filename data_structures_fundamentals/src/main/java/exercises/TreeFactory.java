package exercises;

import java.util.*;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    private Tree<Integer> findRootIteratively() {
        for (Tree<Integer> tree : nodesByKeys.values()) {
            if (tree.getParent() == null) {
                return tree;
            }
        }

        return null;
    }

    private Tree<Integer> findRootRecursively(Tree<Integer> tree) {
        if (tree.getParent() == null) return tree;

        for (Tree<Integer> childTree : tree.getChildren()) {
            findRootRecursively(childTree);
        }

        return null;
    }

    private Tree<Integer> getRoot() {
        List<Tree<Integer>> trees = new ArrayList<>(this.nodesByKeys.values());
//        return this.findRootRecursively(trees.get(0));
        return this.findRootIteratively();
    }


    private Tree<Integer> createNodeByKeyIfAbsent(int key) {
        this.nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return this.nodesByKeys.get(key);
    }

    private void addEdges(int parentKey, int childKey) {
        Tree<Integer> parentTree = this.nodesByKeys.get(parentKey);
        Tree<Integer> childTree = this.nodesByKeys.get(childKey);

        childTree.setParent(parentTree);
        parentTree.addChild(childTree);
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String params : input) {
            int[] data = Arrays.stream(params.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int parentKey = data[0];
            int childKey = data[1];

            this.createNodeByKeyIfAbsent(parentKey);
            this.createNodeByKeyIfAbsent(childKey);

            this.addEdges(parentKey, childKey);
        }

        return this.getRoot();
    }
}
