package linear_data_structures.interfaces;

public interface LinkedList<T> extends Iterable<T> {

    boolean addFirst(T element);

    boolean addLast(T element);

    T getFirst();

    T getLast();

    T remove(T element);

    T removeFirst();

    T removeLast();

    boolean contains(T element);

    boolean isEmpty();

    int size();
}
