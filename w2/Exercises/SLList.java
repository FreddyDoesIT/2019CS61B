package Exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within.
 *
 *
 * First implementation is in a basic way but it is not optimal when scaling up.
 *
 * Second implementation uses a sentinel node to make it better.
 */
public class SLList {

  /**
   * Inner class IntNode which is the actual element of SLList.
   */
  public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
      item = i;
      next = n;
    }
  }

  private IntNode sentinel;
  private int size;

  public SLList(int x) {
    sentinel = new IntNode(0, null);
    sentinel.next = new IntNode(x, null);
    size = 1;
  }

  public SLList() {
    sentinel = new IntNode(0, null);
    size = 0;
  }

  /**
   * Gets the first item of the list.
   * @return the first element of the list
   */
  public int getFirst() {
    return sentinel.next.item;
  }

  /**
   * Adds x to the front of the list.
   * @param x the integer to be added.
   */
  public void addFirst(int x) {
    sentinel.next = new IntNode(x, sentinel.next);
    size += 1;
  }

  /**
   * Adds x to the last of the list
   * @param x the integer to be added
   */
  public void addLast(int x) {
    size += 1;

    IntNode p = sentinel;
    while (p.next != null) {
      p = p.next;
    }

    p.next = new IntNode(x, null);
  }

  /**
   * Returns the size of the list.
   *
   * 1. This implementation actually traverses and counts the number of IntNodes.
   *
   * 2. Find below a common strategy of getting the size of the list.
   *
   * 3. Uses Caching to get the size fast.
   * @return the size
   */
  public int size() {
    return size;
  }

  public static void main(String[] args) {

    SLList l = new SLList();
    l.addLast(100);
    System.out.println(l.size);
    System.out.println(l.getFirst());
    l.addFirst(10);
    l.addFirst(20);
    System.out.println(l.getFirst());
    System.out.println(l.size());


    SLList list = new SLList(5);
    list.addFirst(10);
    list.addFirst(15);

    System.out.println(list.getFirst());

    System.out.println(list.size());
    list.addLast(20);

    System.out.println(list.size());

    List<Integer> test = new ArrayList<>();
    System.out.println( test.size());
    System.out.println(test.get(0));
  }
}
