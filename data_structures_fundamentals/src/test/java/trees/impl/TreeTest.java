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
    public void test_tree_bfs() {
        Integer[] expected = {7, 19, 21, 14, 1, 12, 31, 23, 6};
        List<Integer> actual = this.numbers.orderBfs();

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected[index], number);
            index++;
        }
    }

    @Test
    public void test_tree_dfs() {
        Integer[] expected = {1, 12, 31, 19, 21, 23, 6, 14, 7};
        List<Integer> actual = this.numbers.orderDfs();

        int index = 0;
        for (Integer number : actual) {
            assertEquals(expected[index], number);
            index++;
        }
    }
}