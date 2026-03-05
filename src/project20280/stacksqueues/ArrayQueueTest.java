package project20280.stacksqueues;

import org.junit.jupiter.api.Test;
import project20280.interfaces.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayQueueTest {

    @Test
    void testSize() {
        Queue<Integer> s = new ArrayQueue<>();
        for (int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals(10, s.size());
    }

    @Test
    void testIsEmpty() {
        Queue<Integer> s = new ArrayQueue<>();
        for (int i = 0; i < 10; ++i)
            s.enqueue(i);
        for (int i = 0; i < 10; ++i)
            s.dequeue();
        assertTrue(s.isEmpty());
    }

    @Test
    void testEnqueue() {
        Queue<Integer> s = new ArrayQueue<>();
        for (int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", s.toString());
    }

    @Test
    void testFirst() {
        Queue<Integer> s = new ArrayQueue<>();
        for (int i = 0; i < 10; ++i)
            s.enqueue(i);
        // expect first element to be 0
        assertEquals(Integer.valueOf(0), s.first());
    }

    @Test
    void testDequeue() {
        Queue<Integer> s = new ArrayQueue<>();
        for (int i = 0; i < 10; ++i)
            s.enqueue(i);

        // dequeue should return 0 and reduce size to 9
        assertEquals(Integer.valueOf(0), s.dequeue());
        assertEquals(9, s.size());
    }

}