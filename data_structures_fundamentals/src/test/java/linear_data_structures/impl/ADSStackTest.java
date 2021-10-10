package linear_data_structures.impl;

import linear_data_structures.interfaces.AbstractStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ADSStackTest {
    AbstractStack<String> peopleNames;

    @BeforeEach
    public void setUp() {
        this.peopleNames = new Stack<>();
        this.peopleNames.push("Stefan");
        this.peopleNames.push("Ivan");
        this.peopleNames.push("Joe");
        this.peopleNames.push("Aurora");
    }

    @AfterEach
    public void destruct() {
        this.peopleNames = null;
    }

    @Test
    public void when_push_null_element_should_throw_exception() {
        assertThrows(IllegalArgumentException.class, () -> this.peopleNames.push(null));
    }

    @Test
    public void when_push_NOT_null_element_should_increase_size() {
        assertEquals(4, this.peopleNames.size());

        assertTrue(this.peopleNames.push("George"));

        assertEquals(5, this.peopleNames.size());
    }

    @Test
    public void when_peek_top_most_element_should_be_returned() {
        assertEquals("Aurora", peopleNames.peek());

        this.peopleNames.push("Alison");

        assertEquals("Alison", this.peopleNames.peek());
    }

    @Test
    public void when_pop_on_empty_stack_should_throw_exception() {
        AbstractStack<String> peopleNames = new Stack<>();
        assertThrows(IllegalStateException.class, peopleNames::pop);
    }

    @Test
    public void when_pop_on_NOT_empty_stack_should_decrease_size() {
        this.peopleNames.pop();

        assertEquals(3, this.peopleNames.size());
    }

    @Test
    public void when_pop_on_NOT_empty_stack_should_remove_top_most_element() {
        assertEquals("Aurora", this.peopleNames.pop());
        assertEquals("Joe", this.peopleNames.pop());
        assertEquals("Ivan", this.peopleNames.pop());
    }
}