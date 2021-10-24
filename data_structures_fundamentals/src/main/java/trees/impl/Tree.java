package trees.impl;

import linear_data_structures.impl.Queue;
import linear_data_structures.interfaces.AbstractQueue;
import trees.interfaces.AbstractTree;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static error_messages.TreeErrorMessages.*;

public class Tree<T> implements AbstractTree<T> {
    private T key;
    private Tree<T> parent;
    private List<Tree<T>> children; // -> children

    public Tree(T data, Tree<T>... subtrees) {
        this.key = data;
        this.parent = null;
        this.children = new ArrayList<>();

        for (Tree<T> tree : subtrees) {
            this.children.add(tree);
            tree.parent = this;
        }
    }

    public T getKey() {
        return this.key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Tree<T> getParent() {
        return this.parent;
    }

    public List<Tree<T>> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    private void ensureNotNull(T key) {
        if (key == null) throw new IllegalArgumentException(KEY_CAN_NOT_BE_NULL);
    }

    private void ensureNotNull(Tree<T> node) {
        if (node == null) throw new IllegalArgumentException(NODE_CAN_NOT_BE_NULL);
    }

    private void executeDfs(Tree<T> tree, List<T> result) {
        for (Tree<T> child : tree.children) {
            this.executeDfs(child, result);
        }

        result.add(tree.key);
    }

    private Tree<T> find(T key, Tree<T> node) {
        if (key.equals(node.key)) return node;

        for (Tree<T> child : node.children) {
            Tree<T> result = this.find(key, child);
            if (result != null) return result;
        }

        return null;
    }

    @Override
    public List<T> orderBfs() {
        List<T> result = new ArrayList<>();
        if (this.key == null) {
            return result;
        }

        AbstractQueue<Tree<T>> queue = new Queue<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<T> currentTree = queue.poll();
            result.add(currentTree.key);

            for (Tree<T> child : currentTree.children) {
                queue.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<T> orderDfs() {
        List<T> result = new ArrayList<>();
        if (this.key == null) {
            return result;
        }

        this.executeDfs(this, result);

        return result;
    }

    @Override
    public boolean addChild(T parentKey, Tree<T> child) {
        this.ensureNotNull(parentKey);
        this.ensureNotNull(child);

        Tree<T> parentTree = this.findByKey(parentKey);

        if (parentTree == null)
            throw new IllegalStateException(String.format(NODE_NOT_FOUND, parentKey));

        parentTree.children.add(child);
        child.parent = parentTree;

        return true;
    }

    public Tree<T> findByKey(T key) {
        this.ensureNotNull(key);
        return this.find(key, this);
    }

    @Override
    public T removeByKey(T key) {
        this.ensureNotNull(key);

        Tree<T> toRemove = this.findByKey(key);
        this.ensureNotNull(toRemove);
        T removedKey = toRemove.getKey();

        for (Tree<T> child : toRemove.children) {
            child.parent = null;
        }
        toRemove.children.clear();

        Tree<T> parent = toRemove.parent;
        if (parent == null) {
            this.key = null;
            return removedKey;
        }

        parent.children.remove(toRemove);
        parent = null;
        return removedKey;
    }

    @Override
    public void swapByKeys(T firstKey, T secondKey) {
        this.ensureNotNull(firstKey);
        this.ensureNotNull(secondKey);

        Tree<T> firstNode = this.findByKey(firstKey);
        Tree<T> secondNode = this.findByKey(secondKey);

        this.ensureNotNull(firstNode);
        this.ensureNotNull(secondNode);

        Tree<T> firstParent = firstNode.parent;
        Tree<T> secondParent = secondNode.parent;

        if (firstParent == null) {
            swapRoot(secondNode);
            return;
        }else if (secondParent == null) {
            swapRoot(firstNode);
            return;
        }

        Tree<T> tmp = firstNode;
        firstNode = secondNode;
        secondNode = tmp;

        firstNode.parent = firstParent;
        secondNode.parent = secondParent;

        int firstNodeIndex = firstParent.children.indexOf(secondNode);
        int secondNodeIndex = secondParent.children.indexOf(firstNode);

        firstParent.children.set(firstNodeIndex, firstNode);
        secondParent.children.set(secondNodeIndex, secondNode);
    }

    private void swapRoot(Tree<T> firstNode) {
        this.key = firstNode.key;
        this.parent = null;
        this.children = firstNode.children;
    }
}
