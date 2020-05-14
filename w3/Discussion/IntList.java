package Discussion;

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

  public void print() {
      IntList p = this;
      while (p != null) {
        System.out.print(p.first + "->");
        p = p.rest;
      }
    System.out.println("null");
  }

  public void skippify() {
    IntList p = this;
    int n = 1;
    while (p != null) {
      IntList next = p.rest;
      for (int i = 0; i < n; i++) {
        if (next == null) {
          break;
        }
        next = next.rest;
      }
      n += 1;
      p.rest = next;
      p = p.rest;
    }
  }

  /** Non-destructively creates a copy of x that contains no occurences of y.*/
  public static IntList ilsans(IntList x, int y) {
    IntList copy = new IntList(x.first, null);
    IntList ptr = copy;
    IntList rest = x.rest;
    while (rest != null) {
      int value = rest.first;
      if (value == y) {
        rest = rest.rest;
        continue;
      }
      ptr.rest = new IntList(value, null);
      ptr = ptr.rest;
      rest = rest.rest;
    }
    return copy;
  }

  public static IntList recursiveIlsans (IntList x, int y) {
    if (x == null) {
      return null;
    }
    if (x.first == y) {
      return recursiveIlsans(x.rest, y);
    }

    return new IntList(x.first, recursiveIlsans(x.rest, y));
  }

  /** Destructively modify and return x to contain no occurences of y,
   without using the keyword "new". */
  public static IntList dilsans(IntList x, int y) {
    IntList ptr = x;

    if (ptr.first == y) {
      ptr = ptr.rest.rest;
      x = ptr;
      return x;
    }

    while (ptr.rest != null) {
      int value = ptr.rest.first;
      if (value == y) {
        ptr.rest = ptr.rest.rest;
        ptr = ptr.rest;
        continue;
      }
      ptr = ptr.rest;
    }
    return x;
  }

  public static IntList recursiveDilsans(IntList x, int y) {
    if (x == null) {
      return null;
    }

    x.rest = recursiveDilsans(x.rest, y);
    if (x.first == y) {
      return x.rest;
    }

    return x;
  }

  public static void main(String[] args) {
    IntList list = new IntList(0, null);
    list.rest = new IntList(1, null);
    list.rest.rest = new IntList(2, null);
    list.rest.rest.rest = new IntList(3, null);
    list.rest.rest.rest.rest = new IntList(4, null);
    list.rest.rest.rest.rest.rest = new IntList(5, null);
    list.print();

    IntList test = dilsans(list, 2);
    test.print();
    list.print();



//    list.skippify();
//    list.print();

  }
}

