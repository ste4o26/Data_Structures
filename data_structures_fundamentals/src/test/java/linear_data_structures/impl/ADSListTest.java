package linear_data_structures.impl;

import linear_data_structures.interfaces.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ADSListTest {
    private List<String> numbersAsStrings;

    /**
     * Creating the list with specified initialization and adding several elements into it.
     * When testing other type of list initialization you just need to change the constructor function
     * E.G : new ArrayList(), new LinkedList() ect...
     */
    @BeforeEach
    public void setUp() {
        this.numbersAsStrings = new ArrayList<>();

        this.numbersAsStrings.add("one");
        this.numbersAsStrings.add("two");
        this.numbersAsStrings.add("three");
    }

    @AfterEach
    public void destruct() {
        this.numbersAsStrings = null;
    }

    @Test
    public void whenSizeIsCalledShouldReturnTheNumberOfElementsInList() {
        assertEquals(3, this.numbersAsStrings.size());
    }

    @Test
    public void when_is_empty_is_called_on_empty_list_should_return_true() {
        List<String> numbersAsStrings = new ArrayList<>();
        assertTrue(numbersAsStrings.isEmpty());
    }

    @Test
    public void when_is_empty_is_called_on_NOT_empty_list_should_return_false() {
        assertFalse(this.numbersAsStrings.isEmpty());
    }

    @Test
    public void when_contains_is_called_with_element_that_present_in_list_should_return_true() {
        assertTrue(this.numbersAsStrings.contains("one"));
        assertTrue(this.numbersAsStrings.contains("two"));
        assertTrue(this.numbersAsStrings.contains("three"));
    }

    @Test
    public void when_contains_is_called_with_element_that_NOT_present_in_list_should_return_false() {
        List<String> numbersAsStrings = new ArrayList<>();

        assertFalse(numbersAsStrings.contains("one"));
        assertFalse(numbersAsStrings.contains("three"));
        assertFalse(numbersAsStrings.contains("five"));
    }

    @Test
    public void when_insertAt_is_called_with_valid_index_should_insert_element_on_position() {
        assertTrue(this.numbersAsStrings.insertAt(1, "one and a half"));
        assertEquals("one and a half", this.numbersAsStrings.get(1));
        assertEquals(4, this.numbersAsStrings.size());
    }

    @Test
    public void when_add_is_called_should_append_element_to_the_end_of_list() {
        this.numbersAsStrings.add("four");
        assertEquals(4, numbersAsStrings.size());
        assertEquals("four", this.numbersAsStrings.get(3));
    }

    @Test
    public void when_remove_is_called_with_existing_element_should_remove_it() {
        assertTrue(this.numbersAsStrings.remove("one"));
        assertEquals(2, this.numbersAsStrings.size());
        assertTrue(this.numbersAsStrings.remove("two"));
    }


    @Test
    public void when_remove_is_called_with_NOT_existing_element_should_return_false() {
        List<String> numbersAsStrings = new ArrayList<>();

        assertFalse(numbersAsStrings.remove("two"));
        assertEquals(0, numbersAsStrings.size());
    }

    @Test
    public void when_get_is_called_with_valid_index_should_return_the_element() {
        assertEquals("one", this.numbersAsStrings.get(0));
        assertEquals("two", this.numbersAsStrings.get(1));
        assertEquals("three", this.numbersAsStrings.get(2));
    }

    @Test()
    public void when_get_is_called_with_NOT_valid_index_should_throw_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.numbersAsStrings.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> this.numbersAsStrings.get(-1000));
        assertThrows(IndexOutOfBoundsException.class, () -> this.numbersAsStrings.get(100));
        assertThrows(IndexOutOfBoundsException.class, () -> this.numbersAsStrings.get(1000));
    }

    @Test()
    public void when_set_is_called_with_valid_index_should_overwrite_element_on_index() {
        this.numbersAsStrings.set(0, "minus ten");
        assertEquals("minus ten", this.numbersAsStrings.get(0));
    }

    @Test()
    public void when_set_is_called_with_NOT_valid_index_should_throw_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.numbersAsStrings.set(-1, "ten"));
        assertThrows(IndexOutOfBoundsException.class, () -> this.numbersAsStrings.set(-1000, "ten"));
        assertThrows(IndexOutOfBoundsException.class, () -> this.numbersAsStrings.set(100, "ten"));
        assertThrows(IndexOutOfBoundsException.class, () -> this.numbersAsStrings.set(1000, "ten"));
    }

    @Test()
    public void when_set_is_called_with_null_element_should_throw_exception() {
        assertThrows(IllegalArgumentException.class, () -> this.numbersAsStrings.set(1, null));
    }

}