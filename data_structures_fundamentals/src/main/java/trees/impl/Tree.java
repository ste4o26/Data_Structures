package trees.impl;

import linear_data_structures.impl.Queue;
import linear_data_structures.interfaces.AbstractQueue;
import trees.interfaces.AbstractTree;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> implements AbstractTree<T> {
    private T data;
    private Tree<T> parent;
    private List<Tree<T>> subtrees; // -> children

    public Tree(T data, Tree<T>... subtrees) {
        this.data = data;
        this.parent = null;
        this.subtrees = new ArrayList<>();

        for (Tree<T> tree : subtrees) {
            this.subtrees.add(tree);
        }
    }

    private void executeDfs(Tree<T> tree, List<T> result) {
        for (Tree<T> subtree : tree.subtrees) {
            this.executeDfs(subtree, result);
        }

        result.add(tree.data);
    }

    @Override
    public List<T> orderBfs() {
      List<T> result = new ArrayList<>();
      AbstractQueue<Tree<T>> queue = new Queue<>();
      queue.offer(this);

      while (!queue.isEmpty()) {
          Tree<T> currentTree = queue.poll();
          result.add(currentTree.data);

          for (Tree<T> subtree : currentTree.subtrees) {
              queue.offer(subtree);
          }
      }

      return result;
    }

    @Override
    public List<T> orderDfs() {
        List<T> result = new ArrayList<>();

        this.executeDfs(this, result);

        return result;
    }

    @Override
    public boolean addChild(T parentKey, Tree<T> child) {
        return false;
    }

    @Override
    public T removeNode(T key) {
        return null;
    }

    @Override
    public void swap(T firstKey, T secondKey) {

    }
}
