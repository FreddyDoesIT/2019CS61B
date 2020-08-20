package creatures;

import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import huglife.Action;
import huglife.Direction;
import huglife.Empty;
import huglife.Impassible;
import huglife.Occupant;

import static org.junit.Assert.*;
/**
 * Tests the clorus class.
 */
public class TestClorus {
  @Test
  public void testBasics() {
    // default constructor
    Clorus c = new Clorus();
    assertEquals(1, c.energy(), 0.01);

    Clorus c1 = new Clorus(3);
    assertEquals(3, c1.energy(), 0.01);
  }

  @Test
  public void testChooseAction() {
    Clorus c = new Clorus();
    // test rule1: If there are no empty squares, the Clorus will STAY
    // (even if there are Plips nearby they could attack
    // since plip squares do not count as empty squares).
    Map<Direction, Occupant> allImpassible = new HashMap<>();
    allImpassible.put(Direction.TOP, new Impassible());
    allImpassible.put(Direction.BOTTOM, new Impassible());
    allImpassible.put(Direction.LEFT, new Impassible());
    allImpassible.put(Direction.RIGHT, new Impassible());
    Action action1 = c.chooseAction(allImpassible);
    assertEquals(Action.ActionType.STAY, action1.type);

    Map<Direction, Occupant> leftRightImpassible = new HashMap<>();
    leftRightImpassible.put(Direction.TOP, new Plip());
    leftRightImpassible.put(Direction.BOTTOM, new Plip());
    leftRightImpassible.put(Direction.LEFT, new Impassible());
    leftRightImpassible.put(Direction.RIGHT, new Impassible());
    Action action2 = c.chooseAction(leftRightImpassible);
    assertEquals(Action.ActionType.STAY, action2.type);

    // test rule2:
    // Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
    Map<Direction, Occupant> neighbors3 = new HashMap<>();
    Plip p = new Plip();
    double expectedEnergy = c.energy() + p.energy();
    neighbors3.put(Direction.TOP, p);
    neighbors3.put(Direction.BOTTOM, new Plip());
    neighbors3.put(Direction.LEFT, new Empty());
    neighbors3.put(Direction.RIGHT, new Impassible());
    Action action3 = c.chooseAction(neighbors3);
    assertEquals(Action.ActionType.ATTACK, action3.type);
    assertEquals(expectedEnergy, c.energy(), 0.01);

    // test rule3: Otherwise, if the Clorus has energy greater than or equal to one,
    // it will REPLICATE to a random empty square.
    double e = 2.0;
    assertEquals(e, c.energy(),0.01);
    Map<Direction, Occupant> neighbors4 = new HashMap<>();
    neighbors4.put(Direction.TOP, new Empty());
    neighbors4.put(Direction.BOTTOM, new Impassible());
    neighbors4.put(Direction.LEFT, new Empty());
    neighbors4.put(Direction.RIGHT, new Impassible());
    Action action4 = c.chooseAction(neighbors4);
    assertEquals(Action.ActionType.REPLICATE, action4.type);

    // test rule4: Otherwise, the Clorus will MOVE to a random empty square.
    for (int i = 1; i <= 50; i++) {
      c.move();
    }
    double energyLeft = 2.0 - 0.03 * 50;
    assertEquals(energyLeft, c.energy(), 0.01);
    assertTrue(energyLeft < 1);
    Action action5 = c.chooseAction(neighbors4);
    assertEquals(Action.ActionType.MOVE, action5.type);
  }
}
