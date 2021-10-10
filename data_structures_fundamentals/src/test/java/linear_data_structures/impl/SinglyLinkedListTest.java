package linear_data_structures.impl;

import linear_data_structures.interfaces.LinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    private LinkedList<Integer> numbers;

    @BeforeEach
    void setUp() {
        this.numbers = new SinglyLinkedList<>();
        this.numbers.addLast(10);
        this.numbers.addLast(20);
        this.numbers.addLast(30);
        this.numbers.addLast(40);
    }

    @AfterEach
    void destruct() {
        this.numbers = null;
    }


    @Test
    public void when_add_is_called_should_insert_element_on_the_given_index() {
        this.numbers.add(0, 1);
        this.numbers.add(3, 5);
        this.numbers.add(5, 9);

        assertEquals(1, this.numbers.get(0));
        assertEquals(5, this.numbers.get(3));
        assertEquals(9, this.numbers.get(5));
    }

    @Test
    public void when_add_is_called_should_increase_size_by_1() {
        this.numbers.add(0, 1);
        assertEquals(5, this.numbers.size());
    }

    @Test
    public void when_add_is_called_with_invalid_index_should_throw_exception() {
        assertThrows(IllegalArgumentException.class, () -> this.numbers.add(-100, 2));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.add(-10, 2));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.add(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.add(1, 2));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.add(10, 2));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.add(100, 2));
    }

    @Test
    public void when_addFirst_is_called_should_add_element_at_beginning_of_list() {
        this.numbers.addFirst(3);
        assertEquals(3, this.numbers.getFirst());
    }

    @Test
    public void when_addFirst_is_called_should_increase_size_by_1() {
        this.numbers.addFirst(5);
        assertEquals(5, this.numbers.size());
    }

    @Test
    public void when_addLast_is_called_should_add_element_at_end_of_list() {
        this.numbers.addLast(3);
        assertEquals(3, this.numbers.getLast());
    }

    @Test
    public void when_addLast_is_called_should_increase_size_by_1() {
        this.numbers.addLast(5);
        assertEquals(5, this.numbers.size());
    }

    @Test
    public void when_get_is_called_should_return_element_on_specified_index() {
        assertEquals(10, this.numbers.get(0));
        assertEquals(20, this.numbers.get(1));
        assertEquals(30, this.numbers.get(2));
        assertEquals(40, this.numbers.get(3));
    }

    @Test
    public void when_get_is_called_on_empty_list_should_throw_exception() {
        LinkedList<Integer> numbers = new SinglyLinkedList<>();
        assertThrows(IllegalStateException.class, () -> numbers.get(0));
        assertThrows(IllegalStateException.class, () -> numbers.get(5));
        assertThrows(IllegalStateException.class, () -> numbers.get(10));
        assertThrows(IllegalStateException.class, () -> numbers.get(20));
    }

    @Test
    public void when_get_is_called_with_invalid_index_should_throw_exception() {
        assertThrows(IllegalStateException.class, () -> this.numbers.get(-100));
        assertThrows(IllegalStateException.class, () -> this.numbers.get(-10));
        assertThrows(IllegalStateException.class, () -> this.numbers.get(-1));
        assertThrows(IllegalStateException.class, () -> this.numbers.get(10));
        assertThrows(IllegalStateException.class, () -> this.numbers.get(100));
    }

    @Test
    public void when_getFirst_is_called_should_return_the_first_element() {
        assertEquals(10, this.numbers.getFirst());
    }

    @Test
    public void when_getFirst_is_called_on_empty_list_should_throw_exception() {
        LinkedList<Integer> numbers = new SinglyLinkedList<>();
        assertThrows(IllegalStateException.class, numbers::getFirst);
        assertThrows(IllegalStateException.class, numbers::getFirst);
        assertThrows(IllegalStateException.class, numbers::getFirst);
        assertThrows(IllegalStateException.class, numbers::getFirst);
    }

    @Test
    public void when_getLast_is_called_should_return_the_last_element() {
        assertEquals(40, this.numbers.getLast());
    }

    @Test
    public void when_getLast_is_called_on_empty_list_should_throw_exception() {
        LinkedList<Integer> numbers = new SinglyLinkedList<>();
        assertThrows(IllegalStateException.class, numbers::getLast);
        assertThrows(IllegalStateException.class, numbers::getLast);
        assertThrows(IllegalStateException.class, numbers::getLast);
        assertThrows(IllegalStateException.class, numbers::getLast);
    }

    @Test
    public void when_remove_is_called_should_remove_element_on_specified_index() {
        assertEquals(10, this.numbers.remove(0));
        assertEquals(20, this.numbers.remove(0));
        assertEquals(40, this.numbers.remove(1));
    }

    @Test
    public void when_remove_is_called_with_invalid_index_should_throw_exception() {
        assertThrows(IllegalArgumentException.class, () -> this.numbers.remove(-100));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.remove(-10));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.remove(-1));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.remove(10));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.remove(100));
    }

    @Test
    public void when_remove_is_called_on_empty_list_should_throw_exception() {
        LinkedList<Integer> numbers = new SinglyLinkedList<>();
        assertThrows(IllegalStateException.class, () -> numbers.remove(0));
        assertThrows(IllegalStateException.class, () -> numbers.remove(1));
        assertThrows(IllegalStateException.class, () -> numbers.remove(10));
        assertThrows(IllegalStateException.class, () -> numbers.remove(100));
    }

    @Test
    public void when_contains_is_called_with_element_existing_in_list_should_return_true() {
        assertTrue(this.numbers.contains(10));
        assertTrue(this.numbers.contains(20));
        assertTrue(this.numbers.contains(30));
        assertTrue(this.numbers.contains(40));
    }

    @Test
    public void when_contains_is_called_with_element_NOT_existing_in_list_should_return_false() {
        assertFalse(this.numbers.contains(-10));
        assertFalse(this.numbers.contains(-20));
        assertFalse(this.numbers.contains(50));
        assertFalse(this.numbers.contains(60));
    }

    @Test
    public void when_indexOf_is_called_should_return_index_of_specified_element() {
        assertEquals(0, this.numbers.indexOf(10));
        assertEquals(1, this.numbers.indexOf(20));
        assertEquals(2, this.numbers.indexOf(30));
        assertEquals(3, this.numbers.indexOf(40));
    }

    @Test
    public void when_indexOf_is_called_with_invalid_index_should_throw_exception() {
        assertThrows(IllegalArgumentException.class, () -> this.numbers.indexOf(-100));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.indexOf(-10));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.indexOf(-1));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.indexOf(10));
        assertThrows(IllegalArgumentException.class, () -> this.numbers.indexOf(100));
    }

    @Test
    public void when_indexOf_is_called_on_empty_list_should_throw_exception() {
        assertThrows(IllegalStateException.class, () -> this.numbers.indexOf(-100));
        assertThrows(IllegalStateException.class, () -> this.numbers.indexOf(-10));
        assertThrows(IllegalStateException.class, () -> this.numbers.indexOf(-1));
        assertThrows(IllegalStateException.class, () -> this.numbers.indexOf(0));
        assertThrows(IllegalStateException.class, () -> this.numbers.indexOf(1));
        assertThrows(IllegalStateException.class, () -> this.numbers.indexOf(10));
        assertThrows(IllegalStateException.class, () -> this.numbers.indexOf(100));
    }
}