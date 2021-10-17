package linear_data_structures.impl;

import linear_data_structures.interfaces.AbstractQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ADSQueueTest {
    AbstractQueue<String> laptopsBrands;

    @BeforeEach
    void setUp() {
        this.laptopsBrands = new Queue<>();
        this.laptopsBrands.offer("Lenovo");
        this.laptopsBrands.offer("Razer");
        this.laptopsBrands.offer("Dell");
        this.laptopsBrands.offer("Alien Ware");
    }

    @AfterEach
    void destruct() {
        this.laptopsBrands = null;
    }

    @Test
    public void when_offer_is_called_should_increase_size_by_1() {
        this.laptopsBrands.offer("HP");
        assertEquals(5, this.laptopsBrands.size());
    }

    @Test
    public void when_offer_is_called_should_add_the_element_at_the_end_of_queue() {
        this.laptopsBrands.offer("HP");
        String actual = "";

        for (String current : this.laptopsBrands) {
            actual = current;
        }

        assertEquals("HP", actual);
    }

    @Test
    public void when_peek_is_called_should_return_the_first_element_of_queue() {
        assertEquals("Lenovo", this.laptopsBrands.peek());
    }

    @Test
    public void when_peek_is_called_on_empty_queue_should_throw_exception() {
        AbstractQueue<String> laptopBrands = new Queue<>();
        assertThrows(IllegalStateException.class, laptopBrands::peek);
    }

    @Test
    public void when_poll_is_called_should_decrease_the_size_by_1() {
        this.laptopsBrands.poll();
        assertEquals(3, this.laptopsBrands.size());
    }

    @Test
    public void when_poll_is_called_should_remove_the_first_element_of_queue() {
        String actual = this.laptopsBrands.poll();
        assertEquals("Lenovo", actual);
    }
}