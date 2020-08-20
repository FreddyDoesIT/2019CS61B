package creatures;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.HugLifeUtils;
import huglife.Occupant;

public class Clorus extends Creature {
  /**
   * red color.
   */
  private int r;
  /**
   * green color.
   */
  private int g;
  /**
   * blue color.
   */
  private int b;

  /**
   * Plips should lose 0.15 units of energy when moving.
   */
  private static final double LOST_ENERGY_WHEN_MOVING = 0.03;
  /**
   * Plips should gain energy each time when staying.
   */
  private static final double GAINED_ENERGY_WHEN_STAYING = 0.01;
  /**
   * The min energy of Plips
   */
  private static final int MIN_ENERGY = 0;

  public Clorus (double e) {
    super("clorus");
    energy = e;
    r = 34;
    g = 0;
    b = 231;
  }

  public Clorus () {
    this(1);
  }

  public void move() {
    this.energy -= LOST_ENERGY_WHEN_MOVING;
    if (energy < MIN_ENERGY) {
      energy = MIN_ENERGY;
    }
  }

  public void stay() {
    this.energy += GAINED_ENERGY_WHEN_STAYING;
  }

  public Color color() {
    return new Color(this.r, this.g, this.b);
  }

  public void attack(Creature c) {
    this.energy += c.energy();
  }

  public Clorus replicate() {
    double half = this.energy /= 2;
    Clorus offspring = new Clorus(half);
    return offspring;
  }

  /**
   * Cloruses should obey exactly the following behavioral rules:
   * 1. If there are no empty squares, the Clorus will STAY
   * (even if there are Plips nearby they could attack
   * since plip squares do not count as empty squares).
   *
   * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
   *
   * 3. Otherwise, if the Clorus has energy greater than or equal to one,
   * it will REPLICATE to a random empty square.
   *
   * 4. Otherwise, the Clorus will MOVE to a random empty square.
   *
   * @return Returns an object of type Action.
   */
  public Action chooseAction(Map<Direction, Occupant> neighbors) {
    Deque<Direction> emptyNeighbors = new ArrayDeque<>();
    // rule1
    for (Direction d: neighbors.keySet()){
      if (neighbors.get(d).name().equals("empty")) {
        emptyNeighbors.add(d);
      }
    }

    if (emptyNeighbors.isEmpty()) {
      return new Action(Action.ActionType.STAY);
    }

    // rule2
    Deque<Direction> plips = new ArrayDeque<>();
    for (Direction d: neighbors.keySet()){
      if (neighbors.get(d).name().equals("plip")) {
        plips.add(d);
      }
    }
    if (!plips.isEmpty()) {
      Direction directionToAttack = HugLifeUtils.randomEntry(plips);
      Plip plipToBeAttacked = (Plip) neighbors.get(directionToAttack);
      attack(plipToBeAttacked);
      return new Action(Action.ActionType.ATTACK, directionToAttack);
    }

    Direction empty = HugLifeUtils.randomEntry(emptyNeighbors);
    // rule3
    if (energy() >= 1) {
      return new Action(Action.ActionType.REPLICATE, empty);
    }

    // rule4
    return new Action(Action.ActionType.MOVE, empty);
  }
}
