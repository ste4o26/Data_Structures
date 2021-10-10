package linear_data_structures.interfaces;

public interface List<T> extends Iterable<T>{

    int size();

    int indexOf(T element);

    boolean isEmpty();

    boolean contains(T element);

    boolean insertAt(int index, T element);

    boolean add(T element);

    boolean remove(T element);

    boolean remove(int index);

    T get(int index);

    T set(int index, T element);
}
