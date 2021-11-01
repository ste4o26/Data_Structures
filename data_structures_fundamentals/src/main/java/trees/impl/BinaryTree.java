package trees.impl;

import trees.interfaces.BinaryTreeAbstract;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<T> implements BinaryTreeAbstract<T> {
    private T key;
    private BinaryTree<T> parent;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T key) {
        this.key = key;
    }

    public BinaryTree(T key, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this(key);
        this.leftChild = leftChild;
        this.rightChild = rightChild;

        if (this.leftChild != null) this.leftChild.parent = this;
        if (this.rightChild != null) this.rightChild.parent = this;
    }

    private String getIndentation(int indentationLevel) {
        StringBuilder indentationString = new StringBuilder();
        for (int i = 0; i < indentationLevel; i++) {
            indentationString.append(" ");
        }

        return indentationString.toString();
    }


    @Override
    public T getKey() {
        return this.key;
    }

    @Override
    public BinaryTreeAbstract<T> getLeftChild() {
        return this.leftChild;
    }

    @Override
    public BinaryTreeAbstract<T> getRightChild() {
        return this.rightChild;
    }

    @Override
    public void setKey(T key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indentationLevel) {
        StringBuilder treeAsString = new StringBuilder();
        treeAsString.append(this.getIndentation(indentationLevel))
                .append(this.getKey())
                .append(System.lineSeparator());

        if (this.getLeftChild() != null)
            treeAsString.append(this.getLeftChild().asIndentedPreOrder(indentationLevel + 2));
        if (this.getRightChild() != null)
            treeAsString.append(this.getRightChild().asIndentedPreOrder(indentationLevel + 2));

        return treeAsString.toString();
    }


    @Override
    public List<BinaryTreeAbstract<T>> preOrder() {
        List<BinaryTreeAbstract<T>> nodesPreOrdered = new ArrayList<>();
        nodesPreOrdered.add(this);

        if (this.getLeftChild() != null) nodesPreOrdered.addAll(this.getLeftChild().preOrder());
        if (this.getRightChild() != null) nodesPreOrdered.addAll(this.getRightChild().preOrder());

        return nodesPreOrdered;
    }

    @Override
    public List<BinaryTreeAbstract<T>> inOrder() {
        List<BinaryTreeAbstract<T>> nodesInOrdered = new ArrayList<>();

        if (this.getLeftChild() != null) nodesInOrdered.addAll(this.getLeftChild().inOrder());
        nodesInOrdered.add(this);
        if (this.getRightChild() != null) nodesInOrdered.addAll(this.getRightChild().inOrder());

        return nodesInOrdered;
    }

    @Override
    public List<BinaryTreeAbstract<T>> postOrder() {
        List<BinaryTreeAbstract<T>> nodesPostOrdered = new ArrayList<>();

        if (this.getLeftChild() != null) nodesPostOrdered.addAll(this.getLeftChild().postOrder());
        if (this.getRightChild() != null) nodesPostOrdered.addAll(this.getRightChild().postOrder());
        nodesPostOrdered.add(this);

        return nodesPostOrdered;
    }

    @Override
    public void forEachInOrder(Consumer<T> consumer) {
        if (this.getLeftChild() != null) this.getLeftChild().forEachInOrder(consumer);

        consumer.accept(this.getKey());

        if (this.getRightChild() != null) this.getRightChild().forEachInOrder(consumer);
    }
}
