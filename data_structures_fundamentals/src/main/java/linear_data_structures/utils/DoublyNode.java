package linear_data_structures.utils;

public class DoublyNode<T> {
    private T data;
    private DoublyNode<T> next;
    private DoublyNode<T> previous;

    public DoublyNode(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DoublyNode<T> getNext() {
        return this.next;
    }

    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }

    public DoublyNode<T> getPrevious() {
        return this.previous;
    }

    public void setPrevious(DoublyNode<T> previous) {
        this.previous = previous;
    }
}
