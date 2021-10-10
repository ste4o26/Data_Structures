package linear_data_structures.interfaces;

public interface AbstractStack<T> extends Iterable<T> {

    boolean push(T element);

    T pop();

    T peek();

    int size();

    boolean isEmpty();
}
