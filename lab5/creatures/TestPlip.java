package creatures;
import org.junit.ComparisonFailure;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the plip class
 *  @authr FIXME
 */

public class TestPlip {

    @Test
    public void testBasics() {
        // default constructor
        Plip defaultPlip = new Plip();
        assertEquals(1, defaultPlip.energy(), 0.01);
        // constructor accepting parameter
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);
        p.stay();
        assertEquals(1.90, p.energy(), 0.01);
        p.stay();
        assertEquals(2.00, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        // TODO
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        double half = p.energy() / 2;
        Plip offspring = p.replicate();
        assertEquals(half, p.energy(), 0.01);
        assertEquals(half, offspring.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Plip(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Plip(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Energy < 1; stay.
        p = new Plip(.99);

        actual = p.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy < 1; stay.
        p = new Plip(.99);

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Test if encountering any Clorus,
        // there is 50% probability to MOVE to any available direction.
        HashMap<Direction, Occupant> neighbors = new HashMap<Direction, Occupant>();
        neighbors.put(Direction.TOP, new Clorus());
        neighbors.put(Direction.BOTTOM, new Clorus());
        neighbors.put(Direction.LEFT, new Empty());
        neighbors.put(Direction.RIGHT, new Clorus());

        boolean move = false;
        boolean stay = false;
        while (true) {
            actual = p.chooseAction(neighbors);
            expected = new Action(Action.ActionType.STAY);
            Action expected1 = new Action(Action.ActionType.MOVE, Direction.LEFT);

            try {
                assertEquals(expected1, actual);
                System.out.println("The plip chose to MOVE.");
                move = true;
            } catch (AssertionError failure) {
                assertEquals(expected, actual);
                System.out.println("The plip chose to STAY.");
                stay = true;
            }
            if (move && stay) {
                break;
            }
        }
    }
}
