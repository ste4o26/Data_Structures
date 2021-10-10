package linear_data_structures.interfaces;

public interface LinkedList<T> extends Iterable<T> {
    boolean add(int index, T element);

    boolean addFirst(T element);

    boolean addLast(T element);

    T get(int index);

    T getFirst();

    T getLast();

    T remove(int index);

    T removeFirst();

    T removeLast();

    boolean contains(T element);

    boolean isEmpty();

    int indexOf(T element);

    int size();
}
