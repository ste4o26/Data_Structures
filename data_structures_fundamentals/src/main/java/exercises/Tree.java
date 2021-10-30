package exercises;


import java.util.*;

public class Tree<T> implements AbstractTree<T> {
    private T key;
    private Tree<T> parent;
    private List<Tree<T>> children;

    public Tree(T key, Tree<T>... children) {
        this.key = key;
        this.parent = null;
        this.children = new ArrayList<>();
        for (Tree<T> child : children) {
            this.children.add(child);
            child.setParent(this);
        }
    }

    private boolean isMiddleNode() {
        return !this.getChildren().isEmpty() && this.getParent() != null;
    }

    private boolean isLeafNode() {
        return this.parent != null && this.getChildren().isEmpty();
    }

    private String getIndentation(int indentationLevel) {
        StringBuilder indentationString = new StringBuilder();
        for (int i = 0; i < indentationLevel; i++) {
            indentationString.append("  ");
        }

        return indentationString.toString();
    }

    private int getPathCountToRoot(Tree<T> tree) {
        int pathCount = 0;
        Tree<T> currentTree = tree;
        while (currentTree.getParent() != null) {
            pathCount++;
            currentTree = currentTree.getParent();
        }

        return pathCount;
    }

    private List<T> getMiddleKeysUsingBfs(Tree<T> entryPoint) {
        List<T> middleNodesKeys = new ArrayList<>();
        Deque<Tree<T>> queue = new ArrayDeque<>();

        queue.offer(entryPoint);
        while (!queue.isEmpty()) {
            Tree<T> tree = queue.poll();
            if (tree.isMiddleNode()) {
                middleNodesKeys.add(tree.getKey());
            }

            for (Tree<T> child : tree.getChildren()) {
                queue.offer(child);
            }
        }

        return middleNodesKeys;
    }

    private void constructTreeAsString(StringBuilder result, Tree<T> tree, int indentationLevel) {
        if (tree == null) return;

        result.append(this.getIndentation(indentationLevel++))
                .append(tree.key)
                .append(System.lineSeparator());

        for (Tree<T> child : tree.getChildren()) {
            constructTreeAsString(result, child, indentationLevel);
        }
    }

    private List<T> getMiddleKeysUsingDfs(List<T> middleNodesKeys, Tree<T> tree) {
        if (tree.isMiddleNode()) {
            middleNodesKeys.add(tree.getKey());
        }

        for (Tree<T> child : tree.getChildren()) {
            this.getMiddleKeysUsingDfs(middleNodesKeys, child);
        }

        return middleNodesKeys;
    }

    private Tree<T> getDeepestLeftmostNodeUsingDfs(Tree<T> tree) {
        if (tree.getChildren().isEmpty()) {
            return tree;
        }

        for (Tree<T> child : tree.getChildren()) {
            return this.getDeepestLeftmostNodeUsingDfs(child);
        }

        return null;
    }

    private Tree<T> getDeepestLeftmostNodeUsingBottomUpApproach(Tree<T> entryPoint) {
        int maxPath = 0;
        Tree<T> deepestLeftmostNode = null;
        List<Tree<T>> leafNodes = this.getLeafNodes(entryPoint);

        for (Tree<T> leaf : leafNodes) {
            int currentPath = this.getPathCountToRoot(leaf);
            if (currentPath > maxPath) {
                maxPath = currentPath;
                deepestLeftmostNode = leaf;
            }
        }

        return deepestLeftmostNode;
    }


    private List<Tree<T>> getLeafNodes(Tree<T> entryPoint) {
        ArrayList<Tree<T>> leafNodes = new ArrayList<>();
        Deque<Tree<T>> queue = new ArrayDeque<>();
        queue.offer(entryPoint);

        while (!queue.isEmpty()) {
            Tree<T> tree = queue.poll();
            if (tree.isLeafNode()) leafNodes.add(tree);

            for (Tree<T> child : tree.getChildren()) {
                queue.offer(child);
            }
        }

        return leafNodes;
    }

    private void findLongestPathNodes(List<T> result, Tree<T> tree, int[] args) {
        int currentTreePath = this.getPathCountToRoot(tree);
        int maxPath = args[0];
        if (currentTreePath > maxPath || currentTreePath == 0) {
            args[0] = currentTreePath;
            result.add(tree.getKey());
        }

        for (Tree<T> child : tree.getChildren()) {
            this.findLongestPathNodes(result, child, args);
        }
    }

    @Override
    public T getKey() {
        return this.key;
    }

    @Override
    public Tree<T> getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }


    @Override
    public void addChild(Tree<T> child) {
        this.children.add(child);
        child.setParent(this);
    }

    @Override
    public List<Tree<T>> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    @Override
    public String getAsString() {
        StringBuilder treeKeysAsString = new StringBuilder();
        Tree<T> entryPoint = this;
        int indentationLevel = 0;

        this.constructTreeAsString(treeKeysAsString, entryPoint, indentationLevel);
        return treeKeysAsString.toString().trim();
    }


    @Override
    public List<T> getLeafKeys() {
        List<T> leafKeys = new ArrayList<>();
        Deque<Tree<T>> queue = new ArrayDeque<>();

        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<T> tree = queue.poll();
            if (tree.getChildren().isEmpty()) {
                leafKeys.add(tree.getKey());
            }

            for (Tree<T> child : tree.getChildren()) {
                queue.offer(child);
            }
        }

        return leafKeys;
    }

    @Override
    public List<T> getMiddleKeys() {
        Tree<T> entryPoint = this;
//        return this.getMiddleKeysUsingBfs(entryPoint);
        return this.getMiddleKeysUsingDfs(new ArrayList<>(), entryPoint);
    }

    @Override
    public Tree<T> getDeepestLeftmostNode() {
        Tree<T> entryPoint = this;
//        return this.getDeepestLeftmostNodeUsingDfs(entryPoint);
        return this.getDeepestLeftmostNodeUsingBottomUpApproach(entryPoint);
    }

    @Override
    public List<T> longestPath() {
        List<T> result = new ArrayList<>();
        Tree<T> entryPoint = this;
        int[] args = {0};

        this.findLongestPathNodes(result, entryPoint, args);
        return result;
    }

    @Override
    public List<List<T>> pathsWithGivenSum(int sum) {
        //TODO
        return null;
    }

    @Override
    public List<Tree<T>> subTreesByGivenSum(int sum) {
        //TODO
        return null;
    }
}
