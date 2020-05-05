package Exercises;

public class IntList {
  public int first;
  public IntList rest;

  public IntList(int f, IntList r) {
    first = f;
    rest = r;
  }

  /**
   * Returns the length of a list using... recursion!
   * 1. Definition
   * 2. Scale down
   * 3. Stopping condition
   */
  public int size() {
    // Stopping condition: if this.rest is null, then the size is 1.
    if (this.rest == null) {
      return 1;
    }


    // Scale down, to get the the current list size,
    // we need to get the size of its rest first.
    // So the return value should be current size 1 plus the rest size.
    return this.rest.size() + 1;
  }

  /**
   * Returns the length of a list using... recursion!
   */
  public int iterativeSize() {
    // This p stands for pointer
    // This is a strategy!
    IntList p = this;
    int counter = 0;
    while (p != null) {
      counter++;
      p = p.rest;
    }
    return counter;
  }

  /**
   * Returns the element at the {@code index}.
   */
  public int get(int index) {
    if (index < 0 || index > this.size()) {
      throw new IndexOutOfBoundsException("Index is out of the bound.");
    }

    // Recursion
    // base case or stopping condition
//    if (index == 0) {
//      return this.first;
//    }
//
//    return this.rest.get(index - 1);


    // Iteration
    IntList p = this;
    int counter = 0;

    while (counter != index) {
      p = p.rest;
      counter++;
    }

    return p.first;
  }

  public static void main(String[] args) {
    IntList l;
    l = new IntList(5, null);

    l = new IntList(10, l);
    l = new IntList(15, l);

    System.out.println(l.size());
    System.out.println(l.iterativeSize());
    System.out.println(l.get(0));
    System.out.println(l.get(1));
    System.out.println(l.get(2));
  }
}
