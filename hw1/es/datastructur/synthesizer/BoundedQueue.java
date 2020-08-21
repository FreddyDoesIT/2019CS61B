package es.datastructur.synthesizer;

/**
 * BoundedQueue is similar to Deque but with a more limited API.
 * Items can only be enqueued at the back of the queue,
 * and can only be dequeued from the front of the queue.
 * Unlike our Deque, the BoundedQueue has a fixed capacity,
 * and nothing is allowed to enqueue if the queue is full.
 *
 * All methods that you declare or define are
 * automatically public and abstract (even if you omit the public keyword).
 */
public interface BoundedQueue<T> {

  /**
   *
   * @return return size of the buffer
   */
  int capacity();

  /**
   *
   * @return number of items currently in the buffer
   */
  int fillCount();

  /**
   * add item x to the end
   * @param x
   */
  void enqueue(T x);

  /**
   * delete and return item from the front
   * @return item from the front
   */
  T dequeue();

  /**
   *
   * @return (but do not delete) item from the front
   */
  T peek();

  /**
   * is the buffer empty (fillCount equals zero)?
   * @return true if it is false otherwise
   */
  default boolean isEmpty() {
    return fillCount() == 0;
  }


  /**
   * is the buffer full (fillCount is same as capacity?
   * @return true if it is false otherwise
   */
  default boolean isFull() {
    return fillCount() == capacity();
  }
}
