package exercises;

import java.util.List;

public interface AbstractTree<T> {
    T getKey();

    Tree<T> getParent();

    void setParent(Tree<T> parent);

    void addChild(Tree<T> child);

    String getAsString();

    List<T> getLeafKeys();

    List<T> getMiddleKeys();

    Tree<T> getDeepestLeftmostNode();

    List<List<T>> pathsWithGivenSum();

    List<Tree<T>> subTreesByGivenSum(int sum);
}
