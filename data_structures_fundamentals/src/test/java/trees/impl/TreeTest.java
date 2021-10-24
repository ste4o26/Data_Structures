package trees.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    private Tree<Integer> numbers;

    @BeforeEach
    public void setUp() {
        this.numbers = new Tree<>(7,
                new Tree<>(19,
                        new Tree<>(1),
                        new Tree<>(12),
                        new Tree<>(31)),
                new Tree<>(21),
                new Tree<>(14,
                        new Tree<>(23),
                        new Tree<>(6))
        );
    }

    @AfterEach
    public void destruct() {
        this.numbers = null;
    }

    @Test
    public void when_initialize_new_tree_should_NOT_be_null() {
        assertNotNull(this.numbers);
    }

    @Test
    public void when_orderBfs_is_called_should_return_list_which_order_starts_from_the_leftmost_and_continue_ordering_by_levels() {
        Integer[] expected = {7, 19, 21, 14, 1, 12, 31, 23, 6};
        List<Integer> actual = this.numbers.orderBfs();

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected[index++], number);
        }
    }

    @Test
    public void when_orderDfs_is_called_should_return_list_which_order_starts_from_the_leftmost_and_depth_most_part_of_tree() {
        Integer[] expected = {1, 12, 31, 19, 21, 23, 6, 14, 7};
        List<Integer> actual = this.numbers.orderDfs();

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected[index++], number);
        }
    }

    @Test
    public void when_findByKey_is_called_with_existing_key_should_return_the_tree_with_its_children_and_parent() {
        Tree<Integer> actual = this.numbers.findByKey(19);
        assertEquals(19, actual.getKey());
        assertEquals(3, actual.getChildren().size());
        assertNotNull(actual.getParent());
    }

    @Test
    public void when_findByKey_is_called_on_a_leaf_should_return_the_node_with_zero_children_and_it_parent() {
        Tree<Integer> actual = this.numbers.findByKey(12);
        assertEquals(12, actual.getKey());
        assertEquals(0, actual.getChildren().size());
        assertNotNull(actual.getParent());
    }

    @Test
    public void when_findByKey_is_called_on_the_root_node_should_return_it_without_parent_and_with_its_children() {
        Tree<Integer> actual = this.numbers.findByKey(7);
        assertEquals(7, actual.getKey());
        assertEquals(3, actual.getChildren().size());
        assertNull(actual.getParent());
    }

    public void when_addChild_is_called_should_add_new_child_on_specified_key() {
        Tree<Integer> toAdd = new Tree<>(26);
        this.numbers.addChild(12, toAdd);

        Integer[] expected = {1, 26, 12, 31, 19, 21, 23, 6, 14, 7};
        List<Integer> actual = this.numbers.orderDfs();

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected[index++], number);
        }
    }

    @Test
    public void when_addChild_is_called_should_add_new_child_with_parent_and_zero_children() {
        Tree<Integer> toAdd = new Tree<>(26);
        this.numbers.addChild(12, toAdd);

        Tree<Integer> parent = this.numbers.findByKey(12);
        Tree<Integer> child = this.numbers.findByKey(26);

        assertEquals(parent, child.getParent());
        assertEquals(26, child.getKey());
        assertEquals(0, child.getChildren().size());
    }

    @Test
    public void when_removing_root_node_should_ordering_methods_should_return_empty_list() {
        this.numbers.removeByKey(7);
        List<Integer> actual = this.numbers.orderDfs();
        assertEquals(0, actual.size());
    }

    @Test
    public void when_removing_node_all_its_children_and_itself_should_be_removed() {
        this.numbers.removeByKey(19);
        List<Integer> actual = this.numbers.orderDfs();

        Integer[] expected = {21, 23, 6, 14, 7};
        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected[index++], number);
        }
    }

    @Test
    public void when_swap_nodes_on_same_level_ordering_methods_should_return_different_result_from_before() {
        this.numbers.swapByKeys(19, 14);
        Integer[] expected = {7, 14, 21, 19, 23, 6, 1, 12, 31};
        List<Integer> actual = this.numbers.orderBfs();

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected[index++], number);
        }
    }

    @Test
    public void when_swap_nodes_on_different_level_ordering_methods_should_return_different_result_from_before() {
        this.numbers.swapByKeys(19, 6);
        Integer[] expected = {7, 6, 21, 14, 23, 19, 1, 12, 31};
        List<Integer> actual = this.numbers.orderBfs();
//        Integer[] expected = {6, 21, 23, 1, 12, 31, 19, 14, 7};
//        List<Integer> actual = this.numbers.orderDfs();

        Tree<Integer> first = this.numbers.findByKey(19);
        Tree<Integer> second = this.numbers.findByKey(14);


        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected[index++], number);
        }
    }

    @Test
    public void when_swap_nodes_on_different_levels_parents_of_both_nodes_should_be_swapped_too() {
        this.numbers.swapByKeys(19, 6);

        Tree<Integer> first = this.numbers.findByKey(19);
        Tree<Integer> second = this.numbers.findByKey(6);

        assertEquals(7, second.getParent().getKey());
        assertEquals(14, first.getParent().getKey());
    }

    @Test
    public void when_swap_nodes_on_different_levels_their_parents_should_NOT_contains_the_node_which_is_being_swapped() {
        Tree<Integer> first = this.numbers.findByKey(19);
        Tree<Integer> second = this.numbers.findByKey(6);

        List<Tree<Integer>> firstParentChildren = first.getParent().getChildren();
        List<Tree<Integer>> secondParentChildren = second.getParent().getChildren();

        assertFalse(firstParentChildren.contains(second));
        assertTrue(firstParentChildren.contains(first));

        assertFalse(secondParentChildren.contains(first));
        assertTrue(secondParentChildren.contains(second));
    }

    @Test
    public void when_swap_with_root_should_make_the_other_node_to_be_the_new_root() {
        this.numbers.swapByKeys(7, 14);
        Integer[] expected = {14, 23, 6};
        List<Integer> actual = this.numbers.orderBfs();

        int index = 0;
        for (Integer number : actual) {
            System.out.println(number);
            assertEquals(expected[index++], number);
        }
    }


}