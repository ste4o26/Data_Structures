package linear_data_structures.impl;

import linear_data_structures.interfaces.AbstractQueue;
import linear_data_structures.utils.Node;

import java.util.Iterator;

public class Queue<T> implements AbstractQueue<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public Queue() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }


    private void ensureElementNotNull(T element) {
        if (element == null) throw new IllegalArgumentException("Element can not be null.");
    }

    private void ensureNotEmpty() {
        if (this.isEmpty()) throw new IllegalStateException("Can not execute this operation on empty queue!");
    }

    private void offerNodeIQueueIsEmpty(Node<T> node) {
        if (!this.isEmpty()) return;
        this.head = node;
        this.tail = node;
    }

    private void offerNodeIfQueueNotEmpty(Node<T> node) {
        if (this.isEmpty()) return;

        this.tail.setNext(node);
        this.tail = this.tail.getNext();
    }

    @Override
    public boolean offer(T element) {
        this.ensureElementNotNull(element);
        Node<T> node = new Node<>(element);

        this.offerNodeIQueueIsEmpty(node);
        this.offerNodeIfQueueNotEmpty(node);

        this.size++;
        return true;
    }

    @Override
    public T poll() {
        this.ensureNotEmpty();

        Node<T> nodeToBeDeleted = this.head;
        this.head = nodeToBeDeleted.getNext();

        T deletedElement = nodeToBeDeleted.getData();
        nodeToBeDeleted = null;
        this.size--;

        return deletedElement;
    }

    @Override
    public T peek() {
        this.ensureNotEmpty();
        return this.head.getData();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                T element = currentNode.getData();
                currentNode = currentNode.getNext();
                return element;
            }
        };
    }
}
