package linear_data_structures.impl;

import linear_data_structures.interfaces.LinkedList;
import linear_data_structures.utils.DoublyNode;

import java.util.Iterator;

import static error_messages.ListErrorMessages.*;

public class DoublyLinkedList<T> implements LinkedList<T> {
    private int size;
    private DoublyNode<T> head;
    private DoublyNode<T> tail;

    public DoublyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    private void ensureElementNotNull(T element) {
        if (element == null) throw new IllegalArgumentException(ELEMENT_CANNOT_BE_NULL);
    }

    private void ensureNotEmpty() {
        if (this.isEmpty()) throw new IllegalStateException(OPERATION_CANNOT_BE_EXECUTED_ON_EMPTY_LIST);
    }

    private void linkIfListIsEmpty(DoublyNode<T> node) {
        if (!this.isEmpty()) return;
        this.head = this.tail = node;
    }

    private void linkFirstIfListIsNotEmpty(DoublyNode<T> node) {
        if (this.isEmpty()) return;
        DoublyNode<T> currentHead = this.head;
        currentHead.setPrevious(node);
        node.setNext(currentHead);
        this.head = node;
    }

    private void linkLastIfListIsNotEmpty(DoublyNode<T> node) {
        if (this.isEmpty()) return;
        DoublyNode<T> currentTail = this.tail;
        currentTail.setNext(node);
        node.setPrevious(currentTail);
        this.tail = node;
    }

    private T unlinkNode(DoublyNode<T> toRemove) {
        DoublyNode<T> previousNode = toRemove.getPrevious();
        DoublyNode<T> nextNode = toRemove.getNext();

        if (nextNode == null) {
            this.tail = previousNode;
            if (this.tail != null) this.tail.setNext(null);
        }

        if (previousNode == null) {
            this.head = nextNode;
            if (this.head != null) this.head.setPrevious(null);
        }

        if (previousNode != null && nextNode != null) {
            previousNode.setNext(nextNode);
            nextNode.setPrevious(previousNode);
        }

        T data = toRemove.getData();
        toRemove = null;

        return data;
    }

    @Override
    public boolean addFirst(T element) {
        this.ensureElementNotNull(element);

        DoublyNode<T> node = new DoublyNode<>(element);
        this.linkIfListIsEmpty(node);
        this.linkFirstIfListIsNotEmpty(node);

        this.size++;
        return true;
    }


    @Override
    public boolean addLast(T element) {
        this.ensureElementNotNull(element);

        DoublyNode<T> node = new DoublyNode<>(element);
        this.linkIfListIsEmpty(node);
        this.linkLastIfListIsNotEmpty(node);

        this.size++;
        return true;
    }

    @Override
    public T getFirst() {
        this.ensureNotEmpty();
        return this.head.getData();
    }

    @Override
    public T getLast() {
        this.ensureNotEmpty();
        return this.tail.getData();
    }

    @Override
    public T remove(T element) {
        this.ensureNotEmpty();
        this.ensureElementNotNull(element);

        DoublyNode<T> current = this.head;
        while (current != null) {
            if (element.equals(current.getData())) {
                T removedNodeData = this.unlinkNode(current);
                this.size--;
                return removedNodeData;
            }

            current = current.getNext();
        }

        throw new IllegalArgumentException(NOT_EXISTING_ELEMENT_CANNOT_BE_REMOVED);
    }

    @Override
    public T removeFirst() {
        this.ensureNotEmpty();
        T removedNodeData = this.unlinkNode(this.head);
        this.size--;
        return removedNodeData;
    }

    @Override
    public T removeLast() {
        this.ensureNotEmpty();
        T removedNodeData = this.unlinkNode(this.head);
        this.size--;
        return removedNodeData;
    }

    @Override
    public boolean contains(T element) {
        this.ensureNotEmpty();
        this.ensureElementNotNull(element);

        DoublyNode<T> current = this.head;
        while (current != null) {
            if (element.equals(current.getData())) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            DoublyNode<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                T data = currentNode.getData();
                currentNode = currentNode.getNext();
                return data;
            }
        };
    }
}
