import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArraySet<T> implements Iterable<T>{
  private int size;
  private T[] items;
  public ArraySet() {
    size = 0;
    items = (T[]) new Object[100];
  }

  /* Returns true if this map contains a mapping for the specified key.
   */
  public boolean contains(T x) {
    for(int i = 0; i < size; i++) {
      if (items[i].equals(x)) {
        return true;
      }
    }
    return false;
  }

  /* Associates the specified value with the specified key in this map.
     Throws an IllegalArgumentException if the key is null. */
  public void add(T x) {
    if (x == null) {
      throw new IllegalArgumentException("Null cannot be added.");
    }
    if (!contains(x)) {
      items[size++] = x;
    }

    return;
  }

  /* Returns the number of key-value mappings in this map. */
  public int size() {
    return size;
  }

  /**
   * In Java, static methods do not recognize generic type T, therefore we use a G instead
   * of a T to represent its generic meaning.
   * @param stuff
   * @param <G>
   * @return
   */
  public static <G>ArraySet of(G ... stuff) {
    ArraySet<G> set = new ArraySet<>();
    if (stuff == null || stuff.length == 0) {
      return set;
    }
    for (G g: stuff) {
      set.add(g);
    }
    return set;
  }

  public static void main(String[] args) {
    ArraySet<String> s = new ArraySet<>();
    try {
      s.add(null);
    } catch (IllegalArgumentException e) {
      System.out.println("Detected exception: null trying to be added.");
    }

    s.add("horse");
    s.add("fish");
    s.add("house");
    s.add("fish");
//    System.out.println(s.contains("horse"));
//    System.out.println(s.size());

    /**
     * When developing here, think about how compiler works for
     * built-in API such as List or Set.
     *
     * Three questions need to be thought about:
     * 1. how to return an iterator object
     * 2. does this iterator object have hasNext()?
     * 3. does it have next()?
     *
     * Last but not the least:
     * Implement your own iterator object.
     */
//    Iterator<String> seer = s.iterator();
//    while (seer.hasNext()) {
//      System.out.println(seer.next());
//    }

//    for (String str: s){
//      System.out.println(str);
//    }

    System.out.println(s.toString());
    System.out.println(s);

    ArraySet<Integer> ans = ArraySet.of(1,2,3);
    System.out.println(ans);
  }

  @Override
  public Iterator<T> iterator() {
    return new ArraySetIterator();
  }

  private class ArraySetIterator implements Iterator<T> {
    int wizPos;
    ArraySetIterator() {
      wizPos = 0;
    }
    @Override
    public T next() {
      T returnItem = items[wizPos++];
      return returnItem;
    }

    public boolean hasNext() {
      return wizPos < size();
    }
  }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */

//  @Override
//  public String toString() {
//    StringBuilder sb = new StringBuilder();
//    sb.append("[");
//    for(int i = 0; i < size - 1; i++) {
//      sb.append(items[i].toString() + ",");
//    }
//    sb.append(items[size - 1].toString());
//    sb.append("]");
//    return sb.toString();
//  }

  /**
   * Java 8 introduces {@code join} method which can facilitate here.
   * @return a string
   */
  @Override
  public String toString() {
//    List<T> list = Arrays.asList(items);
//    List<String> l = (List<String>) list;
//    String s = String.join(",", l);
//    return s;

    List<String> list = new ArrayList<>();
    for (T x: this) {
      list.add(x.toString());
    }
    return "[" + String.join(", ", list) + "]";
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (other.getClass() != this.getClass()) {
      return false;
    }
    ArraySet<T> o = (ArraySet<T>) other;
    if (this.size() != o.size()) {
      return false;
    }
    for (T t: items) {
      if (!o.contains(t)) {
        return false;
      }
    }
    return true;
  }

}