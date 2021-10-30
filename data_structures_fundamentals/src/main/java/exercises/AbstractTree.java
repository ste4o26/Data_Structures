package exercises;

import java.util.List;

public interface AbstractTree<T> {
    T getKey();

    Tree<T> getParent();

    void setParent(Tree<T> parent);

    void addChild(Tree<T> child);

    List<Tree<T>> getChildren();

    String getAsString();

    List<T> getLeafKeys();

    List<T> getMiddleKeys();

    Tree<T> getDeepestLeftmostNode();

    List<T> longestPath();

    List<List<T>> pathsWithGivenSum(int sum);

    List<Tree<T>> subTreesByGivenSum(int sum);
}
