package trees.impl;

import trees.interfaces.Heap;

import java.util.Iterator;

/**
 * Heap uses either complete binary tree or perfect binary tree.
 * The elements should be filled from top to bottom and from left to right
 * <p>
 * Heap properties:
 * Min heap property -> root element <= children
 * Max heap property -> root element >= children
 */
public class MaxHeap<T extends Comparable<T>> implements Heap<T> {
    private static final int INITIAL_SIZE = 7;
    private Object[] elements;
    private int size;

    public MaxHeap() {
        this.elements = new Object[INITIAL_SIZE];
        this.size = 0;
    }

    private void ensureNotNull(T element) {
        if (element == null) throw new IllegalArgumentException("Element can not be null!");
    }

    private void ensureIndexInBounds(int index) {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException(String
                    .format("Index %d is not valid for size %d", index, this.size()));
    }

    private T getAt(int index) {
        this.ensureIndexInBounds(index);
        return (T) this.elements[index];
    }

    private void setAt(int index, T element) {
        this.ensureIndexInBounds(index);
        this.ensureNotNull(element);
        this.elements[index] = element;
    }

    private Object[] growElementsTimesTwo() {
        Object[] grownElements = new Object[this.elements.length * 2];
        for (int i = 0; i < this.elements.length - 1; i++) {
            grownElements[i] = this.elements[i];
        }

        return grownElements;
    }

    private int getParentIndex(int childIndex) {
        this.ensureIndexInBounds(childIndex);
        return (childIndex - 1) / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
        this.ensureIndexInBounds(parentIndex);
        return (2 * parentIndex) + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        this.ensureIndexInBounds(parentIndex);
        return (2 * parentIndex) + 2;
    }

    private boolean isLessThan(T first, T second) {
        this.ensureNotNull(first);
        this.ensureNotNull(second);
        return first.compareTo(second) > 0;
    }

    private Object[] heapifyUp(int index) {
        while (index > 0) {
            T child = getAt(index);
            T parent = getAt(getParentIndex(index));

            if (isLessThan(child, parent)) {
                this.setAt(index, parent);
                this.setAt(getParentIndex(index), child);
            }

            index = getParentIndex(index);
        }

        return this.elements;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(T element) {
        this.ensureNotNull(element);
        if (this.size == this.elements.length - 1) this.elements = this.growElementsTimesTwo();

        this.setAt(this.size, element);
        this.elements = this.heapifyUp(this.size);
        this.size++;
    }

    @Override
    public T peek() {
        return this.getAt(0);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size();
            }

            @Override
            public T next() {
                return getAt(currentIndex++);
            }
        };
    }
}
