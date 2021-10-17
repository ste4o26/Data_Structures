package linear_data_structures.impl;

import linear_data_structures.interfaces.AbstractQueue;
import linear_data_structures.utils.Node;

import java.util.Iterator;

import static error_messages.QueueErrorMessages.ELEMENT_CANNOT_BE_NULL;
import static error_messages.QueueErrorMessages.OPERATION_CANNOT_BE_EXECUTED_ON_EMPTY_QUEUE;

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
        if (element == null) throw new IllegalArgumentException(ELEMENT_CANNOT_BE_NULL);
    }

    private void ensureNotEmpty() {
        if (this.isEmpty()) throw new IllegalStateException(OPERATION_CANNOT_BE_EXECUTED_ON_EMPTY_QUEUE);
    }

    private void offerNodeIfQueueIsEmpty(Node<T> node) {
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

        this.offerNodeIfQueueIsEmpty(node);
        this.offerNodeIfQueueNotEmpty(node);

        this.size++;
        return true;
    }

    /**
     * Whenever poll is called and the size of the queue is 1
     * line 62 "this.head = nodeToBeDeleted.getNext();"
     * set the head to null because the next element is null
     * because there is no next element since the size is 1
     */
    @Override
    public T poll() {
        this.ensureNotEmpty();

        Node<T> nodeToBeDeleted = this.head;
        this.head = nodeToBeDeleted.getNext();

        if (this.size() == 1) this.tail = null;

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
