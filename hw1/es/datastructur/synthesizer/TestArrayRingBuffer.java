package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        // test capacity
        int expected = 5;
        int actual = arb.capacity();
        assertEquals(expected, actual);
        // test fillCount
        expected = 0;
        actual = arb.fillCount();
        assertEquals(expected, actual);
        // test enqueue
        arb.enqueue(100);
        expected = 1;
        actual = arb.fillCount();
        assertEquals(expected, actual);
        // test peek
        expected = 100;
        actual = (int)arb.peek();
        assertEquals(expected, actual);
        // test dequeue
        expected = 100;
        actual = (int)arb.dequeue();
        assertEquals(expected, actual);

        expected = 0;
        actual = arb.fillCount();
        assertEquals(expected, actual);

        ArrayRingBuffer arb2 = new ArrayRingBuffer(5);
        // test first index
        arb2.enqueue(101);
        arb2.enqueue(102);
        arb2.enqueue(103);
        arb2.enqueue(104);
        arb2.enqueue(105);

        try{
            arb2.enqueue(106);
        } catch (RuntimeException ex) {
            System.out.println("rb overflow.");
        }

        expected = 101;
        actual = (int)arb2.dequeue();
        assertEquals(expected, actual);

        expected = 102;
        actual = (int)arb2.peek();
        assertEquals(expected, actual);

        expected = 102;
        actual = (int)arb2.dequeue();
        assertEquals(expected, actual);

        arb2.dequeue();
        arb2.dequeue();
        arb2.dequeue();

        arb2.enqueue(1000);
        expected = 1000;
        actual = (int)arb2.peek();
        assertEquals(expected, actual);
    }
}
