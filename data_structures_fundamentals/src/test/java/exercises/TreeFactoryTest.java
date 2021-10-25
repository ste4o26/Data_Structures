package exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeFactoryTest {
    private TreeFactory treeFactory;

    @BeforeEach
    public void setUp() {
        this.treeFactory = new TreeFactory();
    }

    @AfterEach
    public void destruct() {
        this.treeFactory = null;
    }

    @Test
    public void should_create_tree_based_on_input_string_array() {
        String input[] = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "19 12",
                "19 31",
                "14 23",
                "14 6" };


        Tree<Integer> treeFromStrings = this.treeFactory.createTreeFromStrings(input);
        assertEquals(7, treeFromStrings.getKey());
    }
}