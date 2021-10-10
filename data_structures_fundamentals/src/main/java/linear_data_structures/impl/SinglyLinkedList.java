package linear_data_structures.impl;

import linear_data_structures.interfaces.LinkedList;
import linear_data_structures.utils.Node;

import java.util.Iterator;

public class SinglyLinkedList<T> implements LinkedList<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public SinglyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }


    @Override
    public boolean add(int index, T element) {
        return false;
    }

    @Override
    public boolean addFirst(T element) {
        return false;
    }

    @Override
    public boolean addLast(T element) {
        return false;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
