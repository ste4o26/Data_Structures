package trees.interfaces;

public interface Heap<T> extends Iterable<T> {
    int size();

    void add(T element);

    T peek();
}
