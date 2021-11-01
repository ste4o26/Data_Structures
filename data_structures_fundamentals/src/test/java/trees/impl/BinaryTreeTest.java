package trees.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trees.interfaces.BinaryTreeAbstract;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    private BinaryTreeAbstract<Integer> numbers;

    @BeforeEach
    public void setUp() {
        this.numbers = new BinaryTree<>(17,
                new BinaryTree<>(9, new BinaryTree<>(3, null, null),
                        new BinaryTree<>(11, null, null)),
                new BinaryTree<>(25, new BinaryTree<>(20, null, null),
                        new BinaryTree<>(31, null, null)));
    }

    @AfterEach
    public void destruct() {
        this.numbers = null;
    }

    @Test
    public void when_asIndentedPreOrder_should_return_the_tree_representation_as_string_in_depth_first_order_with_indentation() {
        String expected = "17\r\n" +
                "  9\r\n" +
                "    3\r\n" +
                "    11\r\n" +
                "  25\r\n" +
                "    20\r\n" +
                "    31\r\n";

        String actual = this.numbers.asIndentedPreOrder(0);
        assertEquals(expected, actual);
    }

    @Test
    public void when_preOrder_should_return_all_nodes_pre_order() {
        List<Integer> expected = List.of(17, 9, 3, 11, 25, 20, 31);
        List<BinaryTreeAbstract<Integer>> nodesPreOrdered = this.numbers.preOrder();

        int index = 0;
        for (BinaryTreeAbstract<Integer> node : nodesPreOrdered) {
            assertEquals(expected.get(index++), node.getKey());
        }
    }

    @Test
    public void when_inOrder_should_return_all_nodes_in_order() {
        List<Integer> expected = List.of(3, 9, 11, 17, 20, 25, 31);
        List<BinaryTreeAbstract<Integer>> nodesInOrdered = this.numbers.inOrder();

        int index = 0;
        for (BinaryTreeAbstract<Integer> node : nodesInOrdered) {
            assertEquals(expected.get(index++), node.getKey());
        }
    }

    @Test
    public void when_postOrder_should_return_all_nodes_post_order() {
        List<Integer> expected = List.of(3, 11, 9, 20, 31, 25, 17);
        List<BinaryTreeAbstract<Integer>> nodesPostOrdered = this.numbers.postOrder();

        int index = 0;
        for (BinaryTreeAbstract<Integer> node : nodesPostOrdered) {
            assertEquals(expected.get(index++), node.getKey());
        }
    }

    @Test
    public void when_forEachInOrder_should_apply_the_given_consumer_on_every_node_using_inOrder_approach() {
        List<Integer> expected = List.of(3, 9, 11, 17, 20, 25, 31);
        List<Integer> actual = new ArrayList<>();
        this.numbers.forEachInOrder(actual::add);

        assertEquals(expected.size(), actual.size());

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected.get(index++), number);
        }
    }
}