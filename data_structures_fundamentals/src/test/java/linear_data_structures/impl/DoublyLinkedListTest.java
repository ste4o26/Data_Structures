package linear_data_structures.impl;

import linear_data_structures.interfaces.LinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    private LinkedList<Integer> numbers;

    @BeforeEach
    void setUp() {
        this.numbers = new DoublyLinkedList<>();
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
    public void when_getFirst_is_called_should_return_the_first_element() {
        assertEquals(10, this.numbers.getFirst());
    }

    @Test
    public void when_getFirst_is_called_on_empty_list_should_throw_exception() {
        LinkedList<Integer> numbers = new DoublyLinkedList<>();
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
        LinkedList<Integer> numbers = new DoublyLinkedList<>();
        assertThrows(IllegalStateException.class, numbers::getLast);
        assertThrows(IllegalStateException.class, numbers::getLast);
        assertThrows(IllegalStateException.class, numbers::getLast);
        assertThrows(IllegalStateException.class, numbers::getLast);
    }

    @Test
    public void when_remove_is_called_should_remove_specified_element() {
        assertEquals(10, this.numbers.remove(10));
        assertEquals(20, this.numbers.remove(20));
        assertEquals(40, this.numbers.remove(40));
    }

    @Test
    public void when_remove_is_called_with_null_element_should_throw_exception() {
        assertThrows(IllegalArgumentException.class, () -> this.numbers.remove(null));
    }

    @Test
    public void when_remove_is_called_on_empty_list_should_throw_exception() {
        LinkedList<Integer> numbers = new DoublyLinkedList<>();
        assertThrows(IllegalStateException.class, () -> numbers.remove(-20));
        assertThrows(IllegalStateException.class, () -> numbers.remove(null));
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
}