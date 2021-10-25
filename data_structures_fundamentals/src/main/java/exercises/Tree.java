package exercises;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Tree<T>> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    @Override
    public void addChild(Tree<T> child) {
        this.children.add(child);
        child.setParent(this);
    }

    @Override
    public String getAsString() {
        return null;
    }

    @Override
    public List<T> getLeafKeys() {
        return null;
    }

    @Override
    public List<T> getMiddleKeys() {
        return null;
    }

    @Override
    public Tree<T> getDeepestLeftmostNode() {
        return null;
    }

    @Override
    public List<List<T>> pathsWithGivenSum() {
        return null;
    }

    @Override
    public List<Tree<T>> subTreesByGivenSum(int sum) {
        return null;
    }
}
