package linear_data_structures.impl;

import linear_data_structures.interfaces.AbstractStack;
import linear_data_structures.utils.Node;

import java.util.Iterator;

import static error_messages.StackErrorMessages.ELEMENT_CANNOT_BE_NULL;
import static error_messages.StackErrorMessages.OPERATION_CANNOT_BE_EXECUTED_ON_EMPTY_STACK;

public class Stack<T> implements AbstractStack<T> {
    private Node<T> top;
    private int size;

    public Stack() {
        this.size = 0;
        this.top = null;
    }

    private void pushNodeIfStackIsEmpty(Node<T> node) {
        if (!this.isEmpty()) return;
        this.top = node;
    }

    private void pushNodeIfStackNotEmpty(Node<T> node) {
        if (this.isEmpty()) return;
        node.setNext(this.top);
        this.top = node;
    }

    private void ensureElementNotNull(T element) {
        if (element == null) throw new IllegalArgumentException(ELEMENT_CANNOT_BE_NULL);
    }

    @Override
    public boolean push(T element) {
        this.ensureElementNotNull(element);

        Node<T> node = new Node<T>(element);
        this.pushNodeIfStackIsEmpty(node);
        this.pushNodeIfStackNotEmpty(node);

        this.size++;
        return true;
    }

    @Override
    public T pop() {
        if (this.isEmpty()) throw new IllegalStateException(OPERATION_CANNOT_BE_EXECUTED_ON_EMPTY_STACK);

        Node<T> currentTopElement = this.top;
        this.top = this.top.getNext();

        T data = currentTopElement.getData();
        currentTopElement = null;

        this.size--;
        return data;
    }

    @Override
    public T peek() {
        return this.top.getData();
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
            Node<T> currentNode = top;

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
