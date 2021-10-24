package trees.interfaces;

import trees.impl.Tree;

import java.util.List;

public interface AbstractTree<T> {

    List<T> orderBfs();

    List<T> orderDfs();

    boolean addChild(T parentKey, Tree<T> child);

    T removeByKey(T key);

    void swapByKeys(T firstKey, T secondKey);
}
