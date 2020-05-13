package proj1a;

public class LinkedListDeque<T> {
  private static class ItemNode<T> {
    private T item;
    private ItemNode prev;
    private ItemNode next;
    ItemNode(T item, ItemNode prev, ItemNode next) {
      this.item = item;
      this.prev = prev;
      this.next = next;
    }
  }

  private ItemNode sentinel;
  private int size;
  /**
   * Creates an empty linked list deque.
   */
  public LinkedListDeque() {
    sentinel = new ItemNode(0, null, null);
    size = 0;
  }

  /**
   * Creates a deep copy of {@code other}.
   */
  public LinkedListDeque(LinkedListDeque other) {
    size = 0;
    sentinel = new ItemNode(0, null, null);
    for (int i = 0; i < other.size(); i++) {
      addLast((T)other.get(i));
    }
  }

  /**
   * Adds an item of type T to the front of the deque.
   */
  public void addFirst(T item) {
    ItemNode first = new ItemNode(item, sentinel, sentinel.next);
    sentinel.next = first;

    if (first.next == null) {
      first.next = sentinel;
      sentinel.prev = first;
    } else {
      first.next.prev = first;
    }

    size += 1;
  }

  /**
   * Adds an item of type T to the back of the deque.
   */
  public void addLast(T item) {
    if (isEmpty()) {
      addFirst(item);
    } else {
      ItemNode oriLast = sentinel.prev;
      ItemNode last = new ItemNode(item, oriLast, sentinel);
      sentinel.prev = last;
      oriLast.next = last;
      size += 1;
    }
  }

  /**
   * Returns true if deque is empty, false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of items in the deque.
   */
  public int size() {
    return size;
  }

  /**
   * Prints the items in the deque from first to last, separated by a space.
   * Once all the items have been printed, print out a new line.
   */
  public void printDeque() {
    ItemNode pointer = sentinel;
    while (size != 0 && pointer.next != sentinel) {
      System.out.print(pointer.next.item + " ");
      pointer = pointer.next;
      if (pointer == sentinel) {
        break;
      }
    }
    System.out.println();
  }

  /**
   * Removes and returns the item at the front of the deque.
   * If no such item exists, returns null.
   */
  public T removeFirst() {
    if (isEmpty()) {
      return null;
    }

    ItemNode first = sentinel.next;
    T item = (T) first.item;
    ItemNode second = first.next;
    sentinel.next = second;
    second.prev = sentinel;
    nullifyItemNode(first);
    size -= 1;

    return item;
  }

  /**
   * Removes and returns the item at the back of the deque.
   * If no such item exists, returns null.
   */
  public T removeLast() {
    if (isEmpty()) {
      return null;
    }

    T item;
    if (size == 1) {
      item = removeFirst();
    } else {
      ItemNode last = sentinel.prev;
      item = (T)last.item;
      ItemNode secondToLast = last.prev;
      secondToLast.next = sentinel;
      sentinel.prev = secondToLast;
      nullifyItemNode(last);
      size -= 1;
    }
    return item;
  }

  private void nullifyItemNode(ItemNode itemNode) {
    itemNode.prev = null;
    itemNode.item = null;
    itemNode.next = null;
  }

  /**
   * Gets the item at the given index, where 0 is the front,
   * 1 is the next item, and so forth.
   * If no such item exists, returns null. Must not alter the deque!
   */
  public T get(int index) {
    if (index < 0 || index >= size || size == 0) {
      return null;
    }

    ItemNode pointer = sentinel;
    pointer = pointer.next;
    int counter = 0;
    while (counter != index) {
      pointer = pointer.next;
      counter += 1;
    }
    T item = (T)pointer.item;
    return item;
  }

  private ItemNode finder(ItemNode node, int count, int index) {
      if (count == index) {
        return node;
      }

      return finder(node.next, count + 1, index);
  }

  /**
   * Same as get, but uses recursion.
   */
  public T getRecursive(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    ItemNode target = finder(sentinel.next, 0, index);
    return (T)target.item;
  }

  public static void main(String[] args) {
    LinkedListDeque<Integer> lld = new LinkedListDeque<>();
    System.out.println(lld.removeFirst());
    System.out.println(lld.removeLast());
    lld.addLast(88);
    lld.addFirst(5);
    lld.addLast(10);
    lld.addFirst(1);
    lld.addLast(20);
    lld.addFirst(20);
    lld.addLast(17);
    lld.addLast(173);
    lld.addLast(173173);
    lld.printDeque();
    System.out.println("size is: " + lld.size());
    System.out.println(lld.removeFirst());
    lld.printDeque();
    System.out.println("size is: " + lld.size());
    System.out.println(lld.removeLast());
    lld.printDeque();
    System.out.println("size is: " + lld.size());

    for (int i = 0; i < lld.size; i++) {
      System.out.println("Position " + i + ": " + lld.get(i));
    }

    for (int i = 0; i < lld.size; i++) {
      System.out.println("Position " + i + ": " + lld.getRecursive(i));
    }

    LinkedListDeque<Integer> lld2 = new LinkedListDeque<>(lld);
    System.out.println("lld2.size is: " + lld2.size());
  }
}
