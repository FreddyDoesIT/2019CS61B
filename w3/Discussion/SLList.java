package Discussion;

import java.util.ArrayList;
import java.util.List;

import Project0.In;

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
    sentinel.next = new IntNode(x, sentinel.next);
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
    IntNode p = sentinel;
    while (p.next != null) {
      p = p.next;
    }

    p.next = new IntNode(x, null);
    size += 1;
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

  public void insert (int item, int position) {
    if (position >= size) {
      addLast(item);
      return;
    }
    if (position == 0) {
      addFirst(item);
      return;
    }

    IntNode pointer = sentinel;
    int count = 0;
    while (pointer.next != null) {
      if (count == position) {
        break;
      }
      pointer = pointer.next;
      count += 1;
    }

    pointer.next = new IntNode(item, pointer.next);
    size += 1;
  }

  public void reverse () {
    // special case
    // SLList is empty or there is only one node in the list
    IntNode first = sentinel.next;
    sentinel.next = null;
    if (first == null || first.next == null) {
      return;
    }

    IntNode prev = null;
    IntNode next = null;
    while (first != null) {
      next = first.next;
      first.next = prev;
      prev = first;
      first = next;
    }
    first = prev;
    sentinel.next = first;
  }

  public void recursiveReverse() {
    // definition
    // scale down
    // stopping condition
    IntNode first = sentinel.next;
    sentinel.next = reverseRecursively(first);
  }
  // definition
  private IntNode reverseRecursively(IntNode head) {
    // stopping condition
    if (head == null || head.next == null) {
      return head;
    }
    // scale down: to divide the list to head and the rest
    // 1->2->null 2->1->null
    IntNode rest = reverseRecursively(head.next);
    head.next.next = head;
    head.next = null;

    return rest;
  }

  public void printList() {
    IntNode p = sentinel;
    StringBuilder sb = new StringBuilder();
    while (p.next != null) {
      p = p.next;
      sb.append(p.item + "->");
    }
    System.out.println(sb.delete(sb.length() - 2, sb.length()));
  }

  public static void main(String[] args) {

    SLList l = new SLList();
    for (int i = 0; i < 3; i++) {
      l.addLast(i);
    }
    l.printList();
    l.reverse();
    l.printList();
    l.recursiveReverse();
    l.printList();
  }
}
