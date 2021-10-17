package linear_data_structures.impl;

import linear_data_structures.interfaces.List;

import java.util.Iterator;

import static error_messages.ListErrorMessages.ELEMENT_CANNOT_BE_NULL;
import static error_messages.ListErrorMessages.OUT_OF_BOUNDS_ERROR_MESSAGE;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 4;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    private void ensureIndexInBounds(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(
                    String.format(OUT_OF_BOUNDS_ERROR_MESSAGE, index, this.size));
        }
    }

    private void ensureElementNotNull(T element) {
        if (element == null) throw new IllegalArgumentException(ELEMENT_CANNOT_BE_NULL);
    }

    private boolean hasEnoughCapacity() {
        return this.size < this.elements.length;
    }

    private boolean hasMoreThanNeededCapacity() {
        return this.elements.length >= this.size * 3;
    }

    private <T> T[] growElements() {
        int doubledCapacity = this.elements.length * 2;
        Object[] grownElementsArray = new Object[doubledCapacity];

        for (int i = 0; i < this.size; i++) {
            grownElementsArray[i] = this.elements[i];
        }

        return (T[]) grownElementsArray;
    }

    private <T> T[] shrinkElements() {
        int doublyDividedCapacity = this.elements.length / 2;
        Object[] shrankElementsArray = new Object[doublyDividedCapacity];

        for (int i = 0; i < this.size; i++) {
            shrankElementsArray[i] = this.elements[i];
        }

        return (T[]) shrankElementsArray;
    }

    private void shiftLeft(int startIndex) {
        for (int currentIndex = startIndex; currentIndex < this.size - 1; currentIndex++) {
            int nextElementIndex = currentIndex + 1;
            this.elements[currentIndex] = this.elements[nextElementIndex];
        }
    }

    private void shiftRight(int endIndex) {
        for (int currentIndex = this.size - 1; currentIndex >= endIndex; currentIndex--) {
            int nextElementIndex = currentIndex + 1;
            this.elements[nextElementIndex] = this.elements[currentIndex];
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(T element) {
        int index = this.indexOf(element);
        return index != -1;
    }

    @Override
    public boolean insertAt(int index, T element) {
        this.ensureIndexInBounds(index);
        this.ensureElementNotNull(element);

        if (!hasEnoughCapacity()) this.elements = this.growElements();
        this.shiftRight(index);
        this.elements[index] = element;
        this.size++;

        return true;
    }

    @Override
    public boolean add(T element) {
        this.ensureElementNotNull(element);
        if (!hasEnoughCapacity()) this.elements = this.growElements();
        this.elements[this.size] = element;
        this.size++;

        return true;
    }

    @Override
    public boolean remove(T element) {
        if (hasMoreThanNeededCapacity()) this.elements = this.shrinkElements();

        int indexToRemove = this.indexOf(element);
        if (indexToRemove == -1) return false;

        this.shiftLeft(indexToRemove);
        this.size--;
        return true;
    }

    @Override
    public boolean remove(int index) {
        this.ensureIndexInBounds(index);
        if (hasMoreThanNeededCapacity()) this.elements = this.shrinkElements();
        this.shiftLeft(index);
        this.size--;
        return true;
    }

    @Override
    public T get(int index) {
        this.ensureIndexInBounds(index);
        return (T) this.elements[index];
    }

    @Override
    public T set(int index, T element) {
        this.ensureIndexInBounds(index);
        this.ensureElementNotNull(element);
        this.elements[index] = element;
        return element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return this.currentIndex < size();
            }

            @Override
            public T next() {
                return get(this.currentIndex++);
            }
        };
    }
}
