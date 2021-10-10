package linear_data_structures.interfaces;

public interface AbstractQueue<T> extends Iterable<T> {
    boolean offer(T element);

    T poll();

    T peek();

    int size();

    boolean isEmpty();
}
