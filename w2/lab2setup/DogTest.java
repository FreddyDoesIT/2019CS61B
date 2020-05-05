package lab2setup;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DogTest {    
    @Test
    public void testSmall() {
        Dog d = new Dog(3);
        assertEquals("yip", d.noise());
    }

    @Test
    public void testLarge() {
        Dog d = new Dog(20);
        assertEquals("bark", d.noise());
    }
}
