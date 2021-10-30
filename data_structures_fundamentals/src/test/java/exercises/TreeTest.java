package exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    private Tree<Integer> numbers;

    @BeforeEach
    public void setUp() {
        String input[] = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6"};

        TreeFactory treeFactory = new TreeFactory();
        this.numbers = treeFactory.createTreeFromStrings(input);
    }

    @AfterEach
    public void destruct() {
        this.numbers = null;
    }

    @Test
    public void when_getAsString_should_return_string_representation_of_the_tree_where_each_level_of_it_is_with_two_indentations_more() {
        String expected = "7\r\n" +
                "  19\r\n" +
                "    1\r\n" +
                "    12\r\n" +
                "    31\r\n" +
                "  21\r\n" +
                "  14\r\n" +
                "    23\r\n" +
                "    6";

        assertEquals(expected, this.numbers.getAsString());
    }

    @Test
    public void when_getLeafKeys_should_return_all_leaf_keys_of_the_tree() {
        List<Integer> expected = List.of(1, 6, 12, 21, 23, 31);
        List<Integer> actual = this.numbers.getLeafKeys()
                .stream()
                .sorted((f, s) -> f.compareTo(s))
                .collect(Collectors.toList());

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected.get(index++), number);
        }
    }

    @Test
    public void when_getMiddleKeys_should_return_all_keys_of_nodes_which_have_both_child_and_parent() {
        List<Integer> expected = List.of(19, 14);
        List<Integer> actual = this.numbers.getMiddleKeys();

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected.get(index++), number);
        }
    }

    @Test
    public void when_getDeepestLeftmostNode_should_return_the_leftmost_node() {
        assertEquals(1, this.numbers.getDeepestLeftmostNode().getKey());
    }

    /**
     * If several paths have the same longest length return the keys from the leftmost
     */
    @Test
    public void when_longestPath_should_return_list_with_the_keys_of_longest_path() {
        List<Integer> expected = List.of(7, 19, 1);
        List<Integer> actual = this.numbers.longestPath();

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected.get(index++), number);
        }
    }
}