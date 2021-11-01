package trees.interfaces;

import java.util.List;
import java.util.function.Consumer;

public interface BinaryTreeAbstract<T> {
    T getKey();

    BinaryTreeAbstract<T> getLeftChild();

    BinaryTreeAbstract<T> getRightChild();

    void setKey(T key);

    String asIndentedPreOrder(int indent);

    List<BinaryTreeAbstract<T>> preOrder();

    List<BinaryTreeAbstract<T>> inOrder();

    List<BinaryTreeAbstract<T>> postOrder();

    void forEachInOrder(Consumer<T> consumer);
}
