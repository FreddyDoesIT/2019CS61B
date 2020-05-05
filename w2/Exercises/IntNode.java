package Exercises;

// Moved this class to SLList as an inner class
// and make the code style better.
public class IntNode {
  public int item;
  public IntNode next;

  public IntNode(int i, IntNode n) {
    item = i;
    next = n;
  }
}
