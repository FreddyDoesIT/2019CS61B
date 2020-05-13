package proj1a;

public class ArrayDeque<T> {

  private T[] items;
  private int size;
  private int front;
  private int rear;
  private static final int INIT_CAPACITY = 8;
  private static final int RFACTOR = 2;
  private static final double MIN_USAGE_RATIO = 0.25;
  /**
   * Creates an empty array based deque.
   */
  public ArrayDeque () {
    items = (T[]) new Object[INIT_CAPACITY];
    front = 0;
    rear = 1;
    size = 0;
  }

  /**
   * Creates an array based deque with
   * a deep copy of {@code other}.
   * @param other an array based deque
   */
  public ArrayDeque (ArrayDeque other) {
    front = 0;
    rear = 1;
    size = 0;
    items = (T[]) new Object[other.size()];
    for (int i = 0; i < other.size(); i++) {
      addLast((T) other.get(i));
    }
  }

  private void resize(int capacity) {
    T[] newDeque = (T[]) new Object[capacity];
    int originalFront = plusOne(front);
    /* Copy old values to the newly created array. */
    for (int i = 0; i < size; i++) {
      newDeque[i] = items[originalFront];
      originalFront = plusOne(originalFront);
    }

    items = newDeque;
    // at this point, rear is at the start of the empty part of the newly created list,
    // and front is at the end of the empty part.
    front = capacity - 1;
    rear = size;
  }

  private void upSize() {
    resize(size * RFACTOR);
  }

  private void downSize() {
    resize(size / RFACTOR);
  }

  private int minusOne(int index) {
    return (index - 1 + items.length) % items.length;
  }

  private int plusOne(int index) {
    return (index + 1) % items.length;
  }

  /**
   * Checks if current deque is sparse or not.
   * For arrays of length 16 or more,
   * the usage factor should always be at least 25%.
   */
  private boolean isSparse() {
    return size >= 16
            && size < items.length * MIN_USAGE_RATIO;
  }

  /**
   * Checks if current deque is full or not.
   */
  private boolean isFull() {
    return size == items.length;
  }

  /**
   * Adds an item of type T to the front of the deque.
   */
  public void addFirst(T item) {
    int index = front;
    items[index] = item;
    size += 1;
    front = minusOne(index);

    // in case the printDeque is called before resize
    if (isFull()) {
      upSize();
    }
  }

  /**
   * Adds an item of type T to the back of the deque.
   */
  public void addLast(T item) {
    int index = rear;
    items[index] = item;
    size += 1;
    rear = plusOne(index);

    // in case the printDeque is called before resize
    if (isFull()) {
      upSize();
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
    for (int i = plusOne(front); i != rear; i = plusOne(i)) {
      System.out.print(items[i] + " ");
    }
    System.out.println();
  }

  /**
   * Removes and returns the item at the front of the deque.
   * If no such item exists, returns null.
   */
  public T removeFirst() {
    return remove(front);
  }

  /**
   * Removes and returns the item at the back of the deque.
   * If no such item exists, returns null.
   */
  public T removeLast() {
    return remove(rear);
  }

  private T remove(int index) {
    if (isEmpty()) {
      return null;
    }

    int realIndex = 0;
    if (index == front) {
      realIndex = plusOne(index);
      front = realIndex;
    }
    if (index == rear) {
      realIndex = minusOne(index);
      rear = realIndex;
    }

    T toRemove = items[realIndex];
    items[realIndex] = null;
    size -= 1;

    if (isSparse()) {
      downSize();
    }

    return toRemove;
  }

  /**
   * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
   * If no such item exists, returns null. Must not alter the deque!
   */
  public T get(int index) {
    if (index >= size || index < 0) {
      return null;
    }

    int first = plusOne(front);
    int realIndex = (first + index) % items.length;
    return items[realIndex];
  }

  public static void main(String[] args) {
    ArrayDeque<Integer> ad = new ArrayDeque<>();
    for (int i = 0; i < 8; i++) {
      ad.addFirst(i);
    }
    ad.printDeque();

    ArrayDeque<Integer> ad2 = new ArrayDeque<>(ad);
    ad2.printDeque();
  }
}
